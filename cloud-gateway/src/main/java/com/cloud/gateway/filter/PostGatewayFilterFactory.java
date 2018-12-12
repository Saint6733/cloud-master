package com.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * 网关过滤器
 * @author 黄志强
 * @data 2018/12/6 11:00
 */
public class PostGatewayFilterFactory extends AbstractGatewayFilterFactory<PostGatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(PostGatewayFilterFactory.Config config) {
        //从配置文件中获取配置
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                ServerHttpResponse response = exchange.getResponse();
                //以某种方式操作响应
                response.getStatusCode();
            }));
        };
    }

    public static class Config{
        //编写过滤器属性
    }
}
