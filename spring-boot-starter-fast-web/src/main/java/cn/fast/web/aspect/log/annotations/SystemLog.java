package cn.fast.web.aspect.log.annotations;


import cn.fast.web.aspect.log.enums.LogType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    /**
     * 服务id
     * @return
     */
    String serviceId();

    /**
     * 模块名
     * @return
     */
    String moduleName();

    /**
     * 操作名
     * @return
     */
    String actionName();
    /**
     * 描述
     *
     * @return {String}
     */
    String value();
    /**
     * 日志类型
     * @return
     */
    LogType type() default LogType.OPERATION;

}
