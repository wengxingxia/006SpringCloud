package com.xander.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * Description: 不管请求路径是什么，固定转发路径为 /payment/get/2 的Filter
 *
 * @author Xander
 * datetime: 2021-03-23 20:45
 */
@Component
public class FixedPathGateWayFilter extends AbstractGatewayFilterFactory {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            logger.info("请求路径："+request.getURI().getPath());
            //新建 ServerHttpRequest 实例，固定转发路径为 /payment/get/2
            ServerHttpRequest newRequest = request.mutate().path("/payment/get/2").build();
            //重新build exchange
            exchange = exchange.mutate().request(newRequest).build();
            logger.info("固定后路径："+exchange.getRequest().getURI().getPath());
            return chain.filter(exchange);
        };
    }
}
