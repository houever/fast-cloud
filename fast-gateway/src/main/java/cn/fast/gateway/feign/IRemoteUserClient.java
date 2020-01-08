package cn.fast.gateway.feign;

import cn.fast.common.ServiceConstants;
import cn.fast.gateway.config.KeepErrMsgConfiguration;
import cn.fast.gateway.feign.fallback.RemoteUserClientFallback;
import cn.fast.web.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = ServiceConstants.USC_SERVICE,fallback = RemoteUserClientFallback.class,configuration = {KeepErrMsgConfiguration.class})
public interface IRemoteUserClient {

    @PostMapping(value = "/account/info",produces = "application/json;charset=UTF-8")
    Result queryAllMenusByName(@RequestParam("username") String username);

    @GetMapping(value = "/sysLoginConfig/bg")
    Result getSysLoginBg();
}
