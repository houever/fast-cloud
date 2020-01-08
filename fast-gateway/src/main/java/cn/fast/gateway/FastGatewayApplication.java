package cn.fast.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringCloudApplication
public class FastGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastGatewayApplication.class, args);
    }

}
