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
 * 类名称：GlobalGatewayFilter<br>
 * 类描述：全局过滤器，暂不做任何处理 <br>
 * 创建时间：2018年11月30日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@Component
@Slf4j
public class GlobalGatewayFilter implements GlobalFilter,Ordered {
	/**
	 * 验证是否有token
	 * @param exchange
	 * @param chain
	 * @return
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		/*String token = exchange.getRequest().getQueryParams().getFirst("token");
		if (token==null || token.isEmpty()){
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			LOGGER.error("token为空-----------------------------");
			return exchange.getResponse().setComplete();
		}*/
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return -600;
	}
}
