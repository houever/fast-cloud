package cn.fast.gateway.common;

import cn.fast.web.common.model.OAuth2AccessToken;
import cn.fast.web.common.result.Result;
import cn.fast.web.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
@Slf4j
public class GrantTokenUtil {
    private static final String disabled="User is disabled";
    private static final String expired="User account has expired";
    private static final String locked="User account is locked";
    private static final String badcredentials="Bad credentials";
    private static final String credentialsexpired="User credentials have expired";

    private static final String BAD="User is disabled";

    public static Result grantUserToken(OAuth2AccessToken oAuth2AccessToken) {
        if (StringUtils.isNotBlank(oAuth2AccessToken.getAccess_token())) {
            return Result.success(oAuth2AccessToken.getAccess_token());
        } else {
            log.debug("登录失败:错误码:{},信息:{}", oAuth2AccessToken.getError(), oAuth2AccessToken.getMessage());
            if(disabled.equals(oAuth2AccessToken.getMessage())){
                return Result.fail(ResultEnum.LOGIN_USER_disabled);
            }else if(expired.equals(oAuth2AccessToken.getMessage())){
                return Result.fail(ResultEnum.LOGIN_USER_expired);
            }else if(locked.equals(oAuth2AccessToken.getMessage())){
                return Result.fail(ResultEnum.LOGIN_USER_locked);
            }else if(credentialsexpired.equals(oAuth2AccessToken.getMessage())){
                return Result.fail(ResultEnum.LOGIN_USER_credentialsexpired);
            }
            return Result.fail(ResultEnum.LOGIN_USER_ERR);
        }
    }
}
