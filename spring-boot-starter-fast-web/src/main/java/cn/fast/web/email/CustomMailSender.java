package cn.fast.web.email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author houqj
 * @date 2020-04-17 9:36
 */
@Slf4j
@Component
public class CustomMailSender {
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    public Configuration configuration;//freemarker---FreeMarkerConfigurer

    @Value("${spring.mail.username}")
    public String USER_NAME;//发送者

    //文本分割
    static {
        System.setProperty("mail.mime.splitlongparameters", "false");
    }

    public void send(Email mail) {
        try {
            log.info("发送普通文本邮件：{}", mail.getContent());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(USER_NAME);
            message.setTo(mail.getTo());
            message.setSubject(mail.getSubject());
            message.setText(mail.getContent());
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFreemarker(Email mail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //这里可以自定义发信名称比如：工作流
            helper.setFrom(USER_NAME, "系统邮件通知");
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            Map<String, Object> model = new HashMap<>();
            model.put("mail", mail);
            model.put("exception", mail.getKvMap());
            Template template = configuration.getTemplate(mail.getTemplate());
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
