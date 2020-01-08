package cn.fast.gateway.config;

import cn.fast.web.common.result.Result;
import cn.fast.web.common.utils.GsonUtil;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * @author houqj
 * @date 2019-12-26 12:58
 */
@Slf4j
public class KeepErrMsgConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }
    /**
     * 自定义错误
     */
    public class UserErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = null;
            try {
                // 获取原始的返回内容
                String json = Util.toString(response.body().asReader());
                exception = new RuntimeException(json);
                // 将返回内容反序列化为Result，这里应根据自身项目作修改
                Result result = GsonUtil.json2Bean(json,Result.class);
                // 业务异常抛出简单的 RuntimeException，保留原来错误信息
                if (!result.isSuccess()) {
                    exception = new RuntimeException(result.getMessage());
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
            return exception;
        }
    }
}
