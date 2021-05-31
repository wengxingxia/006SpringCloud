package com.xander.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description: 启动类
 *
 * @author Xander
 * datetime: 2021-05-31 15:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Gateway6001 {

    public static void main(String[] args) {
        SpringApplication.run(Gateway6001.class, args);
    }

}
