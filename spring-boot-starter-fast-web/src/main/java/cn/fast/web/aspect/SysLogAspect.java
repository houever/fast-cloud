package cn.fast.web.aspect;

import cn.fast.web.aspect.log.annotations.SystemLog;
import cn.fast.web.aspect.log.model.SysLog;
import cn.fast.web.aspect.log.utils.IpInfoUtil;
import cn.fast.web.aspect.log.utils.SysLogUtils;
import cn.fast.web.common.constant.Constant;
import cn.fast.web.common.constant.SecurityConstants;
import cn.fast.web.common.model.OAuth2AccessToken;
import cn.fast.web.common.utils.GsonUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.NamedThreadLocal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author houqijun
 * @Description 统计系统操作日志
 * @Date 12:29 2019/3/3 0003
 * @Param
 * @return
 **/
@Slf4j
@Aspect
public class SysLogAspect {

    @Resource
    HttpServletRequest request;

    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");


    @Resource
    private RabbitTemplate rabbitTemplate;


    /**
     * Controller层切点,返回值方式
     */
    //@Pointcut("execution(* *..controller..*Controller*.*(..))")
    @Pointcut("@annotation(cn.fast.web.aspect.log.annotations.SystemLog)")
    //@Pointcut("execution(public cn.fast.common.result.Result *(..))")
    public void controllerAspect() {

    }


    @Around("controllerAspect()")
    public Object doAfter(ProceedingJoinPoint point) throws Throwable {
        OAuth2AccessToken oAuth2AccessToken = GsonUtil.gsonToBean(request.getHeader(SecurityConstants.AUTH_HEADER), OAuth2AccessToken.class);

        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        SysLog sysLog = new SysLog();
        SystemLog systemLog = targetMethod.getAnnotation(SystemLog.class);
        //开始时间
        Long startTime = System.currentTimeMillis();


        //请求的class名称
        String strClassName = point.getTarget().getClass().getName();
        //请求的方法名
        String strMethodName = point.getSignature().getName();
        //服务id
        String clientId = systemLog.serviceId();
        //模块名
        String moduleName = systemLog.moduleName();
        //描述
        String desc = systemLog.value();
        //日志类型
        int type = systemLog.type().ordinal();
        //参数
        Object[] args = point.getArgs();
        log.debug("请求参数==>{}",args);
        Method method = methodSignature.getMethod();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        sysLog.setServiceId(clientId)
                .setModuleName(moduleName)
                .setIp(request.getRemoteHost())
                .setRequestUri(URLUtil.getPath(request.getRequestURI()))
                .setMethod(request.getMethod())
                .setUserAgent(request.getHeader("user-agent"))
                .setParams(HttpUtil.toParams(request.getParameterMap()))
                .setIpAddress(IpInfoUtil.getIpCity(request.getRemoteHost()))
                .setTitle(desc)
                .setType(type)
                .setCreateBy(oAuth2AccessToken.getUsername());

        Object obj = null;
        try{
            obj = point.proceed();
            sysLog.setStatus(Constant.SUCCESS);
        }catch (Throwable e){
            sysLog.setException(SysLogUtils.getTrace(e));
            sysLog.setStatus(Constant.FAIL);
        }
        Long endTime = System.currentTimeMillis();
        sysLog.setCostTime(endTime - startTime);
        rabbitTemplate.convertAndSend("sys_log_queue", GsonUtil.gson2String(sysLog));
        //SpringContextHolder.publishEvent(new SysLogEvent(sysLog));
        return obj;

    }
}
