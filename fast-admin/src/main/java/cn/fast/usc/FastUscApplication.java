package cn.fast.usc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author houqj
 * @date 2020-01-07 11:35
 */
@SpringBootApplication
@MapperScan("cn.fast.usc.mapper")
public class FastUscApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastUscApplication.class);
    }
}
