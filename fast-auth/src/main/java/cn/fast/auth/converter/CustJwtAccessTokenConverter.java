package cn.fast.auth.converter;

import cn.fast.auth.service.SysUser;
import cn.fast.web.common.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;


/**
 * @author houqijun
 * @Package com.mycloud.admin.converter
 * @Description: 自定义转换令牌
 *
 * 现在让我们设置一些基础设施，以便能够在访问令牌中添加一些自定义声明。框架提供的标准声明都很好，
 * 但大多数情况下我们需要在令牌中使用一些额外的信息来在客户端使用。 我们将定义一个TokenEnhancer来定制我们的Access Token与这些额外的声明。
 * 在下面的例子中，我们将添加一个额外的字段“组织”到我们的访问令牌 - 与此CustomTokenEnhancer：
 *
 * @date 2018/7/2 14:53
 */
@Slf4j
public class CustJwtAccessTokenConverter extends JwtAccessTokenConverter {

    /**
     * refresh_token 不加密
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Object principal = authentication.getPrincipal();
        log.debug("oauth2token===>{}",authentication);
        log.debug("principal===>{}",principal);
        DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
        String tokenId = result.getValue();
        if (!info.containsKey(TOKEN_ID)) {
            info.put(TOKEN_ID, tokenId);
        }
        info.put("organization", authentication.getName() + randomAlphabetic(4));
        SysUser sysUser = (SysUser) authentication.getUserAuthentication().getPrincipal();
        List<String> authorities = sysUser.getAuthorities().stream().map(f -> f.getAuthority()).collect(Collectors.toList());
        info.put("id", sysUser.getId());
        info.put("username", sysUser.getUsername());
        info.put("deptId", sysUser.getDeptId());
        info.put("tenantId", sysUser.getTenantId());
        info.put("license", SecurityConstants.MYCLOUD_LICENSE);
        info.put("roles", authorities);
        info.put("btns", sysUser.getBtns());
        log.debug("info============>{}",info);
        result.setAdditionalInformation(info);
        result.setValue(encode(result, authentication));
        return result;
    }
}
