package com.xander.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 23:28
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients // 开启 OpenFeign 功能，扫描添加 @FeignClient 的类
public class ConsumerOrder8002 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder8002.class, args);
    }

}