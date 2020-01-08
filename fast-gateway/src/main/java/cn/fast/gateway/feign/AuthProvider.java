package cn.fast.gateway.feign;

import cn.fast.common.ServiceConstants;
import cn.fast.gateway.common.UserDetails;
import cn.fast.web.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = ServiceConstants.AUTH_SERVICE, fallback = AuthProvider.AuthProviderFallback.class)
public interface AuthProvider {

    @GetMapping(value = "/oauth/check_token")
    UserDetails checkToken(@RequestParam(name = "token") String token);

    /**
     * 调用签权服务，判断用户是否有权限
     */
    @PostMapping(value = "/auth/permission")
    Result auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestParam("url") String url, @RequestParam("method") String method);

    @Component
    class AuthProviderFallback implements AuthProvider {
        @Override
        public UserDetails checkToken(String token) {
            return null;
        }

        /**
         * 降级统一返回无权限
         */
        @Override
        public Result auth(String authentication, String url, String method) {
            return Result.fail();
        }
    }
}
