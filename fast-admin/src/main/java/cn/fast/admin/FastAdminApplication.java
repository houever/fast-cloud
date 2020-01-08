package cn.fast.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author houqj
 * @date 2020-01-07 14:37
 */
@MapperScan("cn.fast.*.mapper")
@SpringBootApplication
public class FastAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastAdminApplication.class);
    }
}
