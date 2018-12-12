package com.cloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



/**
 * @author 黄志强
 * @data 2018/12/7 15:26
 */
@Component
@Slf4j
public class AddressFilter implements GlobalFilter,Ordered {

    /**
     * 黑白名单 过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        if (ip.startsWith("10")){
            exchange.getResponse().setStatusCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            LOGGER.error("您已被拉入黑名单");
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        //return -800;
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
