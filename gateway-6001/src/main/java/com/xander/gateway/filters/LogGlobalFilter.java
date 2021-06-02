package com.xander.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Description: 全局GlobalFilter，打印日志
 *
 * @author Xander
 * datetime: 2020-10-22 16:20
 */
@Component
public class LogGlobalFilter implements GlobalFilter, Ordered {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 打印请求uri
        logger.info("全局过滤器LogGlobeFilter开始：" + request.getURI().getPath());
        Mono<Void> mono = chain.filter(exchange);
        logger.info("全局过滤器LogGlobeFilter结束：" + request.getURI().getPath());
        return mono;
    }

    @Override
    public int getOrder() {
        // order: 加载顺序，数值越小，优先级越高
        return 0;
    }
}
