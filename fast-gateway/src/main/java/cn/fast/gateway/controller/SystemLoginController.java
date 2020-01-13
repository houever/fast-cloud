package cn.fast.gateway.controller;

import cn.fast.gateway.config.OAuth2ClientProperties;
import cn.fast.gateway.common.GrantTokenUtil;
import cn.fast.gateway.common.RedisUtil;
import cn.fast.gateway.event.UserSessionEvent;
import cn.fast.gateway.feign.IRemoteUserClient;
import cn.fast.gateway.feign.OAuth2ServerClient;
import cn.fast.gateway.model.LoginParam;
import cn.fast.web.common.constant.SecurityConstants;
import cn.fast.web.common.model.OAuth2AccessToken;
import cn.fast.web.common.result.Result;
import cn.fast.web.common.result.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class SystemLoginController {

    private OAuth2ClientProperties oAuth2ClientProperties;
    private ApplicationContext applicationContext;
    private OAuth2ServerClient oAuth2ServerClient;
    private RestTemplate restTemplate;
    private IRemoteUserClient remoteUserClient;
    private RedisUtil redisUtil;

    /**
     * 通过密码授权方式向授权服务器获取令牌
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public Result login(LoginParam loginParam) throws Exception {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        ResponseEntity<OAuth2AccessToken> responseEntity = getToken(username, password);
        if (StringUtils.isNotBlank(responseEntity.getBody().getAccess_token())) {
            applicationContext.publishEvent(new UserSessionEvent(this,responseEntity.getBody()));
            return Result.success(responseEntity.getBody().getAccess_token());
        }
        log.debug("调用鉴权服务返回====>{}", responseEntity);
        return GrantTokenUtil.grantUserToken(responseEntity.getBody());
    }

    @PostMapping("/logout")
    public Result exit(@RequestHeader(value = "Authorization", required = true) String authToken) {
        String token = authToken.replace("bearer ", "").trim();

        //删除redis中的用户信息
        redisUtil.del(SecurityConstants.USER_TOKEN + token);
        Result result = oAuth2ServerClient.removeToken(token);
        if (!ResultEnum.SUCCESS.getCode().equals(result.getCode())) {
            return Result.fail(ResultEnum.LOGINOUT_FAIL);
        }
        return Result.success("注销成功");
    }

    @GetMapping("/verify/captcha")
    public Result verifyCode() {
        return Result.success("验证码校验成功");
    }

    @GetMapping(value = "/info")
    public Result userInfo(@RequestHeader(value = "Authorization", required = true) String authToken) throws Exception {
        String token = authToken.substring(7);
        OAuth2AccessToken oAuth2AccessToken = (OAuth2AccessToken) redisUtil.get(SecurityConstants.USER_TOKEN + token);
        if (oAuth2AccessToken != null) {
            Result result = remoteUserClient.queryAllMenusByName(oAuth2AccessToken.getUsername());
            return result;
        }
        return Result.fail("获取用户信息失败");
    }

    public ResponseEntity<OAuth2AccessToken> getToken(String userName, String passWord) {
        //Http Basic 验证
        String clientAndSecret = oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret();
        //这里需要注意为 Basic 而非 Bearer
        clientAndSecret = "Basic " + Base64.getEncoder().encodeToString(clientAndSecret.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", clientAndSecret);
        //授权请求信息
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("username", Collections.singletonList(userName));
        map.put("password", Collections.singletonList(passWord));
        map.put("grant_type", Collections.singletonList(oAuth2ClientProperties.getGrantType()));
        map.put("scope", Arrays.asList(oAuth2ClientProperties.getScope()));
        //HttpEntity
        HttpEntity httpEntity = new HttpEntity(map, httpHeaders);
        //获取 Token
        ResponseEntity<OAuth2AccessToken> exchange = restTemplate.exchange(oAuth2ClientProperties.getAccessTokenUri(), HttpMethod.POST, httpEntity, OAuth2AccessToken.class);
        return exchange;
    }
    @GetMapping(value = "/sys/bg")
    public Result getSysBg(){
        Result sysLoginBg = remoteUserClient.getSysLoginBg();
        return sysLoginBg;
    }
}
