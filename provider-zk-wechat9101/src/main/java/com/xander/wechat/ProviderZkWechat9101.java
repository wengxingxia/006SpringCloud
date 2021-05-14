package com.xander.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 22:45
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderZkWechat9101 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderZkWechat9101.class, args);
    }
}
