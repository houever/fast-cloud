package cn.fast.gateway.service.impl;

import cn.fast.gateway.service.IAuthService;
import cn.fast.gateway.common.UserDetails;
import cn.fast.gateway.feign.AuthProvider;
import cn.fast.web.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Slf4j
public class AuthService implements IAuthService {

    @Autowired
    private AuthProvider authProvider;

    /**
     * Authorization认证开头是"bearer "
     */
    private static final int BEARER_BEGIN_INDEX = 7;

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth";

    @Override
    public boolean validToken(String token) {
        //验证token是否有效
        UserDetails userDetails = authProvider.checkToken(token);
        if(StringUtils.isNotBlank(userDetails.getUsername())){
            return true;
        }
        return Boolean.FALSE;
    }

    @Override
    public Result authenticate(String authentication, String url, String method) {
        return authProvider.auth(authentication, url, method);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    @Override
    public boolean hasPermission(Result authResult) {
        return authResult.isSuccess() && (boolean) authResult.getData();
    }

    @Override
    public boolean hasPermission(String authentication, String url, String method) {
        log.debug("service鉴权     authentication=>{},url=>{},method=>{}",authentication,url,method);
        //从认证服务获取是否有权限
        return hasPermission(authenticate(authentication, url, method));
    }
}
