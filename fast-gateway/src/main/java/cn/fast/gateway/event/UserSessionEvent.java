package cn.fast.gateway.event;

import cn.fast.web.common.model.OAuth2AccessToken;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @program: mycloud-admin
 * @description:
 * @author: houqijun
 * @create: 2019-03-16 08:38
 **/
@Getter
public class UserSessionEvent extends ApplicationEvent {

    private OAuth2AccessToken oAuth2AccessToken;

    public UserSessionEvent(Object source,OAuth2AccessToken oAuth2AccessToken) {
        super(source);
        this.oAuth2AccessToken = oAuth2AccessToken;
    }
}
