package com.xander.user.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Description: 应用配置类
 *
 * @author Xander
 * datetime: 2021-05-11 18:18
 */
@Configuration
public class AppConfig {

    /**
     * 配置应用的负载均衡规则为轮询策略 RoundRobinRule
     *
     * @return
     */
    @Bean
    public IRule rule() {
        return new RoundRobinRule();
    }

    @Bean
    @LoadBalanced //开启 Ribbon 功能
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
