package cn.fast.gateway.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houqj
 * @date 2019-08-05 16:59
 */
@Slf4j
@Configuration
public class WebConfiguration{

    /**
     * 后端配置vue history模式
     * 链接: https://blog.csdn.net/trusause/article/details/84299886
     * 链接: https://www.jianshu.com/p/5b24ae4cc159
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            factory.addErrorPages(errorPage404);
        });
    }
}
