package cn.fast.gateway.feign;

import cn.fast.common.ServiceConstants;
import cn.fast.gateway.feign.fallback.OAuth2ServerClientHystrix;
import cn.fast.web.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author huangqi
 *  cn.fast.admin.feign
 * @date 2018/6/28 14:17
 */
@FeignClient(value = ServiceConstants.AUTH_SERVICE, fallback = OAuth2ServerClientHystrix.class)
public interface OAuth2ServerClient {
    @RequestMapping(value = "/oauth/remove_token",method = RequestMethod.POST)
    Result removeToken(@RequestParam("token") String token);
}
