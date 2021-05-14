package com.xander.order8001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 23:28
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerOrder8001 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder8001.class, args);
    }

}