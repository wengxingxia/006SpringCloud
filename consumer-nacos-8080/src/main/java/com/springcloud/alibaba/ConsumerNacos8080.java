package com.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-06-08 17:42
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerNacos8080 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerNacos8080.class, args);
    }
}
