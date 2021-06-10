package com.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Description: 应用配置
 *
 * @author Xander
 * datetime: 2021-06-08 17:44
 */
@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced //开启Ribbon客户端负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
