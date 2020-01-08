package cn.fast.gateway.event;

import cn.fast.gateway.common.RedisUtil;
import cn.fast.web.common.constant.SecurityConstants;
import cn.fast.web.common.model.OAuth2AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @program: mycloud-admin
 * @description:
 * @author: houqijun
 * @create: 2019-03-16 08:39
 **/
@Slf4j
@Component
public class UserSessionEventListener {

    @Resource
    private RedisUtil redisUtil;

    @EventListener
    public void register(UserSessionEvent userSessionEvent){

        OAuth2AccessToken oAuth2AccessToken = userSessionEvent.getOAuth2AccessToken();
        redisUtil.set(SecurityConstants.USER_TOKEN+oAuth2AccessToken.getAccess_token(),oAuth2AccessToken,1, TimeUnit.DAYS);

    }

}
