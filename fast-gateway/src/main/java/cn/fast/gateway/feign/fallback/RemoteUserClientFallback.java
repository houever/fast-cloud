package cn.fast.gateway.feign.fallback;

import cn.fast.gateway.feign.IRemoteUserClient;
import cn.fast.web.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteUserClientFallback implements IRemoteUserClient {

    @Override
    public Result accountInfo(String name) {
        return Result.fail("调用用户服务失败");
    }

    @Override
    public Result getSysLoginBg() {
        return Result.fail("调用用户服务失败");
    }
}
