package cn.fast.web.advice;

import cn.fast.web.common.exception.BusinessExcpetion;
import cn.fast.web.common.exception.GlobalException;
import cn.fast.web.common.result.Result;
import cn.fast.web.common.utils.GsonUtil;
import cn.fast.web.handler.CustomExceptionHandler;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * 描述: controller 层异常处理
 * @author houqj
 * @date 2019/7/24
 **/
@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice implements ResponseBodyAdvice {

    private ThreadLocal<Object> modelHolder = new ThreadLocal<>();

    @Resource
    private CustomExceptionHandler exceptionHandler;

    /*
     * 参数验证提示
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleIllegalParamException(MethodArgumentNotValidException e,HttpServletRequest request) {
        log.error("【【进入异常处理】】，参数验证错误:{},参数==>{}",e.getMessage(),e.getParameter());
        Map<String,String> map = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
        String tips = "非法参数："+ GsonUtil.gson2String(map);
        exceptionHandler.handleException(e,request);
        return Result.fail("5000", tips);
    }

    @ExceptionHandler(BusinessExcpetion.class)
    public Result handleResultException(BusinessExcpetion e,HttpServletRequest request) {
        log.error("【【进入异常处理】】,业务异常:{},信息==>{}",e.getMessage());
        exceptionHandler.handleException(e,request);
        return Result.fail("5000",e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public Result handleResultException(GlobalException e, HttpServletRequest request) {
        log.error("【【进入异常处理】】,全局异常:{},信息==>{}",e.getMessage(),e.getCause());
        log.error("uri={} | requestBody={}", request.getRequestURI(),
                JSON.toJSONString(modelHolder.get()));
        exceptionHandler.handleException(e,request);
        return Result.fail(e.getResultEnum());
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public Result handleResultException(MyBatisSystemException e,HttpServletRequest request) {
        exceptionHandler.handleException(e,request);
        log.error("数据库操作失败:{}",e.getCause());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        exceptionHandler.handleException(e,request);
        return Result.fail("5000", GsonUtil.gson2String(e.getCause()));
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // ModelHolder 初始化
        modelHolder.set(webDataBinder.getTarget());
    }
    
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if ( log.isDebugEnabled()) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            String requestUriWithoutContextPath = servletRequest.getRequestURI().substring(servletRequest.getContextPath().length());
             log.debug("uri={} | responseBody={}", requestUriWithoutContextPath, GsonUtil.gson2String(o));
        }
        return o;
    }
}
