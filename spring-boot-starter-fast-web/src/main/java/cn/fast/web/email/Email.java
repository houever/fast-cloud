package cn.fast.web.email;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Email封装对象
 *
 * @author houqj
 */
@ToString
@Data //该注解会自动生成 getter setter 方法
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    // 必填参数
    private String[] to;// 接收方邮件数组
    private String subject;// 主题,邮件标题
    private String content;// 邮件内容文本
    // 以模板发送邮件的时候填写以下参数的值
    private String template;// 邮件模板
    private HashMap<String, Object> kvMap;// 自定义参数，用于模板发送时显示内容

    public Email() {
        super();
    }

    public Email(String[] to, String subject, String content, String template, HashMap<String, Object> kvMap) {
        super();
        this.to = this.to;
        this.subject = subject;
        this.content = content;
        this.template = template;
        this.kvMap = kvMap;
    }
}
