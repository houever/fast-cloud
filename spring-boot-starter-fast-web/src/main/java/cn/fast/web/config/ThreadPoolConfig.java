package cn.fast.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author houqj
 * @date 2019-09-30 10:28
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize( 10 );//核心线程数
        t.setMaxPoolSize( 50 );//最大线程数
        t.setQueueCapacity(25);//线程池所使用的缓冲队列
        t.setThreadNamePrefix( "My-task===>" );
        return t;
    }
}
