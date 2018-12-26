package com.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * 使用请求 IP 作为限流键的KeyResolver
 * @author 黄志强
 * @data 2018/12/13 17:13
 */
@Component
public class RemoteAddressResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {

        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

}
