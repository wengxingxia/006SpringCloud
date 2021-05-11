package com.xander.payment9001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2021-05-10 22:45
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderPayment9001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPayment9001.class, args);
    }
}
