package cn.fast.web.common.annotations;

import java.lang.annotation.*;

/**
 * @author houqj
 * @date 2019-09-30 10:26
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    String id();

    String userName();

}
