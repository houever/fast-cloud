package cn.fast.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * @author houqj
 * @date 2020-04-17 11:46
 */
@Slf4j
@Component
public class CustomExceptionHandler {

    @Async
    public void handleException(Exception e,HttpServletRequest request){
        //产生异常，发送邮件服务
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        // mailService.send(mail);//发送普通邮件
        HashMap<String, Object> mapParam = new HashMap<>(); //自定义模板参数，用于在ftl中接收展示
        mapParam.put("exceptionCause", e.getCause());
        mapParam.put("exceptionMessage", e.getMessage());
        mapParam.put("exceptionClass", e.getClass());
        log.info("异步处理异常通知:{}",mapParam);
    }
}
