package com.xander.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 路由配置
 *
 * @author Xander
 * datetime: 2021-05-31 16:29
 */
@Configuration
public class RouteConfig {

    /**
     * 自定义 RouteLocator
     * @return
     */
    // @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //以 /payment/ 开头的uri，都转发给 http://localhost:9001
        return builder.routes()
                //路由id: path_route； 匹配的路径：/payment/**
                .route("path_route", r -> r.path("/payment/**")
                        // 匹配成功转发给：http://localhost:9001
                        .uri("http://localhost:9001"))
                .build();
    }

}
