package com.xander.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

// @Configuration
public class AppConfig {

    /**
     * 配置 open feign 的日志打印级别
     * - NONE: 不开启日志(默认)
     * - BASIC: 记录请求方法、URL、响应状态、执行时间
     * - HEADERS: 在BASIC基础上，加上请求头和响应头信息
     * - FULL: 在HEADERS基础上，加上请求和响应的正文及元数据
     *
     * @return
     */
    @Bean
    Logger.Level feignLogLervel() {
        return Logger.Level.FULL;
    }
}
