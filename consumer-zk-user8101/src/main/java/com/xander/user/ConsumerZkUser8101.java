package com.xander.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-14 16:34
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerZkUser8101 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerZkUser8101.class, args);
    }
}
