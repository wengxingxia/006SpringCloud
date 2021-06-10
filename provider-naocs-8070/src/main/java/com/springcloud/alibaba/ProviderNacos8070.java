package com.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-06-08 17:35
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderNacos8070 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderNacos8070.class, args);
    }
}
