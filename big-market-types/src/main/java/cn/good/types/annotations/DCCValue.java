package cn.good.types.annotations;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @Description 动态配置中心
 * @Author wkm
 * @Date 2024/12/15
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DCCValue {
    String value() default "";
}
