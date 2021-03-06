package com.xander.order8001.config;

import brave.sampler.Sampler;
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
    @LoadBalanced //开启Ribbon客户端负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    /**
     * 通过java bean的方式配置 seluth 采样比例
     *
     * @return
     */
    // @Bean
    public Sampler defaultSampler() {
        // Sampler.ALWAYS_SAMPLE表示全部采样；
        // Sampler.NEVER_SAMPLE表示都不采样；
        return Sampler.ALWAYS_SAMPLE;
    }
}
