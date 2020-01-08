package cn.fast.web.mybatisplus.tenant;

import cn.fast.web.common.constant.SecurityConstants;
import cn.fast.web.common.model.OAuth2AccessToken;
import cn.fast.web.common.utils.GsonUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @program: mycloud-admin
 * @description:
 * @author: houqijun
 * @create: 2019-03-15 19:44
 **/
@Slf4j
@UtilityClass
public class UserUtil {

    public OAuth2AccessToken getCurrentUser(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        OAuth2AccessToken oAuth2AccessToken = GsonUtil.gsonToBean(servletRequestAttributes.getRequest().getHeader(SecurityConstants.AUTH_HEADER), OAuth2AccessToken.class);
        log.debug("session 当前用户==>{}",oAuth2AccessToken);
        return oAuth2AccessToken;
    }


}
