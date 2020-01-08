package cn.fast.web.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author houqj
 * @date 2019-12-13 9:54
 */
@Component
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class Swagger2Properties {

    /**
     * 是否启用
     */
    @Value("${swagger.enable}")
    private boolean enable;
    /**
     * 标题
     */
    @Value("${swagger.title}")
    private String title;
    /**
     * 描述
     */
    @Value("${swagger.description}")
    private String description;
    /**
     * 版本
     */
    @Value("${swagger.version}")
    private String version;
    /**
     * termsOfServiceUrl
     */
    @Value("${swagger.termsOfServiceUrl}")
    private String termsOfServiceUrl;
    /**
     * 名称
     */
    @Value("${swagger.contact.name}")
    private String name;
    /**
     * URL
     */
    @Value("${swagger.contact.url}")
    private String url;
    /**
     * Email
     */
    @Value("${swagger.contact.email}")
    private String email;
}
