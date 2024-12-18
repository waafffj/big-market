package cn.good.types.annotations;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/12/18
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RateLimiterAccessInterceptor {
       /** 用哪个字段作为拦截标识，未配置则默认全部*/
      String key() default "all";
      /**
       * 限制频次 (每秒请求次数)
       */
      long permitsPerSecond();
      /** 黑名单拦截 (多少次限制后加入黑名单) 0 不限制*/
      double blacklistCount() default 0;
      /** 拦截后的方法 */
      String fallbackMethod();
}
