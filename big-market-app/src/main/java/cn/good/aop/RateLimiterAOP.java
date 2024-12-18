package cn.good.aop;

import cn.good.types.annotations.DCCValue;
import cn.good.types.annotations.RateLimiterAccessInterceptor;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/12/18
 **/
@Slf4j
@Aspect
@Component
public class RateLimiterAOP {
    @DCCValue("rateLimiterSwitch:close")
    private String rateLimiterSwitch;

    @Resource
    private RedissonClient redissonClient;
    /* 个人限频记录1分钟 */
    private final Cache<String, RateLimiter> loginRecord = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();
    /* 个人限频黑名单24小时 */
    private final Cache<String,Long> blacklist = CacheBuilder.newBuilder()
            .expireAfterWrite(24,TimeUnit.HOURS)
            .build();

    @Pointcut("@annotation(cn.good.types.annotations.RateLimiterAccessInterceptor)")
    public void aopPoint(){

    }
    @Around("aopPoint() && @annotation(rateLimiterAccessInterceptor)")
    public Object doRouter(ProceedingJoinPoint jp, RateLimiterAccessInterceptor rateLimiterAccessInterceptor) throws Throwable {
        /* 限流开关【open 开启】  关闭后不会走限流策略*/
        if(StringUtils.isBlank(rateLimiterSwitch) || "close".equals(rateLimiterSwitch)){
            return jp.proceed();
        }
        String key = rateLimiterAccessInterceptor.key();
        if(StringUtils.isBlank(key)){
            throw new RuntimeException("annotation RateLimiter uId is null！");
        }
        /* 获取拦截字段 */
        String keyAttr = getAttrValue(key,jp.getArgs());
        log.info("aop attr {}",keyAttr);

        /** 黑名单拦截 */
        if(!"all".equals(keyAttr) && rateLimiterAccessInterceptor.blacklistCount() != 0 && null != blacklist.getIfPresent(keyAttr) && blacklist.getIfPresent(keyAttr) > rateLimiterAccessInterceptor.blacklistCount()){
            log.info("限流-黑名单拦截(24h):{}",keyAttr);
            return fallbackMethodResult(jp, rateLimiterAccessInterceptor.fallbackMethod());
        }
        /** 获取限流  -> Guava 缓存1分钟 */
        RateLimiter rateLimiter = loginRecord.getIfPresent(keyAttr);
        if(null == rateLimiter){
            rateLimiter = RateLimiter.create(rateLimiterAccessInterceptor.permitsPerSecond());
            loginRecord.put(keyAttr,rateLimiter);
        }
        /** 限流拦截*/
        if(!rateLimiter.tryAcquire()){
            if(rateLimiterAccessInterceptor.blacklistCount() != 0){
                if(null == blacklist.getIfPresent(keyAttr)){
                    blacklist.put(keyAttr,1L);
                }else {
                    blacklist.put(keyAttr,blacklist.getIfPresent(keyAttr) + 1L);
                }
            }
            log.info("限流-超频次拦截:{}",keyAttr);
            return fallbackMethodResult(jp,rateLimiterAccessInterceptor.fallbackMethod());
        }
        return jp.proceed();
    }

    @Around("aopPoint()")
    public Object rateLimiter(ProceedingJoinPoint point) throws Throwable {
        if(StringUtils.isBlank(rateLimiterSwitch) || "close".equals(rateLimiterSwitch)){
            return point.proceed();
        }
        /** 获取方法签名 */
        MethodSignature signature = (MethodSignature) point.getSignature();
        /** 获取注解 */
        RateLimiterAccessInterceptor rateLimiterAccessInterceptor = signature.getMethod().getAnnotation(RateLimiterAccessInterceptor.class);
        /** 获取注解的key*/
        String key = rateLimiterAccessInterceptor.key();
        if(StringUtils.isBlank(key)){
            throw new RuntimeException("annotation RateLimiter uId is null！");
        }
        /** 获取入参中key字段对应的值 */
        String keyAttr = getAttrValue(key,point.getArgs());
        if(StringUtils.isBlank(keyAttr)){
            throw new RuntimeException("annotation RateLimiter keyAttr is null !");
        }
        /** 用户限流次数缓存*/
        String userBlackKey = keyAttr + "_black";

        RAtomicLong userBlackProxy = redissonClient.getAtomicLong(userBlackKey);
        if(!"all".equals(keyAttr) && rateLimiterAccessInterceptor.blacklistCount() != 0){
            boolean exist = userBlackProxy.isExists();
            /** userBlackProxy存在且其值大于注解中配置的黑名单计数，则记录日志并调用备选方法 */
            if(exist && userBlackProxy.get() > rateLimiterAccessInterceptor.blacklistCount()){
                log.info("redis-限流-黑名单拦截(24h) :{}",userBlackProxy);
                return fallbackMethodResult(point, rateLimiterAccessInterceptor.fallbackMethod());
            } else if (!exist) {
                /** 如果userBlackProxy不存在，则设置其过期时间为24小时。 */
                userBlackProxy.expire(24,TimeUnit.HOURS);
            }
        }
        /** 通过 Redisson 客户端获取一个名为 keyAttr 的限流器（RRateLimiter）对象*/
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(keyAttr);
        if(!rateLimiter.isExists()) {
            /** 配置限流器
             *RateType.OVERALL 表示全局限流，即所有客户端共享限流规则。RateType.PER_CLIENT:表示每个客户端独立限流
             * rateLimiterAccessInterceptor.permitsPerSecond() 为限流速率，即每秒允许的请求次数
             * RateIntervalUnit.SECONDS 表示限流时间单位为秒
             * */
            rateLimiter.trySetRate(RateType.OVERALL,rateLimiterAccessInterceptor.permitsPerSecond(),1, RateIntervalUnit.SECONDS);
        }
        /** 获取用户限流器 如果获取失败（即超过限流规则），则执行以下逻辑*/
        if(!rateLimiter.tryAcquire()){
            if(rateLimiterAccessInterceptor.blacklistCount() != 0){
                userBlackProxy.incrementAndGet();
            }
            log.info("redis-限流-拦截:{}",keyAttr);
            return fallbackMethodResult(point,rateLimiterAccessInterceptor.fallbackMethod());
        }
        return point.proceed();
    }
    /**
     * 调用用户配置的回调方法，当拦截后，返回回调结果。
     */
    private Object fallbackMethodResult(JoinPoint jp,String fallbackMethod) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        Method method = jp.getTarget().getClass().getMethod(fallbackMethod,methodSignature.getParameterTypes());
        return method.invoke(jp.getThis(),jp.getArgs());
    }
    /**
     * 实际根据自身业务调整，主要是为了获取通过某个值做拦截
     */

    public String getAttrValue(String attr,Object[] args){
        if(args[0] instanceof String){
            return args[0].toString();
        }
        String filedValue = null;
        for(Object arg : args){
            try {
                if (StringUtils.isNotBlank(filedValue)){
                    break;
                }
                // filedValue = BeanUtils.getProperty(arg, attr);
                // fix: 使用lombok时，uId这种字段的get方法与idea生成的get方法不同，会导致获取不到属性值，改成反射获取解决
                filedValue = String.valueOf(this.getValueByName(arg,attr));
            }catch (Exception e){
                log.error("获取路由属性值失败 attr：{}", attr, e);
            }
        }
        return filedValue;
    }

    /**
     * 获取对象的特定属性值
     *
     * @param item 对象
     * @param name 属性名
     * @return 属性值
     * @author tang
     */

    private Object getValueByName(Object item,String name){
        try {
            Field field = getFieldByName(item, name);
            if(field == null){
                return null;
            }
            field.setAccessible(true);
            Object o = field.get(item);
            field.setAccessible(false);
            return o;
        }catch (IllegalAccessException e){
            return null;
        }
    }

    /**
     * 根据名称获取方法，该方法同时兼顾继承类获取父类的属性
     *
     * @param item 对象
     * @param name 属性名
     * @return 该属性对应方法
     * @author tang
     */

    private Field getFieldByName(Object item,String name){
        try{
            Field field;
            try {
                field = item.getClass().getDeclaredField(name);
            }catch (NoSuchFieldException e){
                field = item.getClass().getSuperclass().getDeclaredField(name);
            }
            return field;
        }catch (NoSuchFieldException e){
            return null;
        }
    }

}
