package cn.fast.web.common.annotations;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestStr {

    String value();

    boolean required() default true;

    String defaultValue() default "";

}
