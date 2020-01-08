package cn.fast.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "security.oauth2")
public class OAuth2ClientProperties {
    private String clientId;
    private String clientSecret;
    private String userAuthorizationUri;
    private String grantType;
    private String scope;
    private String accessTokenUri;
    private String userInfoUri;
    private String checkTokenAccess;

}
