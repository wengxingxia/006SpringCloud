package com.xander.order8001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 23:28
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix // 开启 Hystrix 功能
public class ConsumerOrder8001 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder8001.class, args);
    }

}