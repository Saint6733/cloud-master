package com.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 类名称：GlobalGatewayFilter<br>
 * 类描述：全局过滤器，暂不做任何处理 <br>
 * 创建时间：2018年11月30日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
public class GlobalGatewayFilter implements GlobalFilter {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return chain.filter(exchange.mutate().build());
	}
}
