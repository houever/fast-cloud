package cn.fast.gateway.feign.fallback;

import cn.fast.gateway.feign.OAuth2ServerClient;
import cn.fast.web.common.result.Result;
import org.springframework.stereotype.Component;

/**
 * @author huangqi
 * @Package com.mycloud.admin.feign.hystrix
 * @date 2018/6/28 14:19
 */
@Component
public class OAuth2ServerClientHystrix implements OAuth2ServerClient {
    @Override
    public Result removeToken(String token) {
        return Result.fail();
    }

}
