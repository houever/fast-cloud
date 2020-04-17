package cn.fast.web.handler;

import cn.fast.web.email.Email;
import cn.fast.web.email.CustomMailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
    @Resource
    private CustomMailSender customMailSender;

    @Value("${alarm.email}")
    private String[] email;

    @Async
    public void handleException(Exception e,HttpServletRequest request){
        //产生异常，发送邮件服务
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        Email mail = new Email();
        mail.setEmail(email);
        mail.setSubject("异常告警邮件通知");
        mail.setContent(stringWriter.toString());
        // mailService.send(mail);//发送普通邮件
        mail.setTemplate("notifyEmail.ftl");
        HashMap<String, Object> mapParam = new HashMap<>(); //自定义模板参数，用于在ftl中接收展示
        mapParam.put("exceptionCause", e.getCause());
        mapParam.put("exceptionMessage", e.getMessage());
        mapParam.put("exceptionClass", e.getClass());
        mail.setKvMap(mapParam);
        customMailSender.sendFreemarker(mail);//发送模板邮件
    }
}
