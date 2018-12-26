package com.cloud.gateway.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 网关限流过滤器 ，基于令牌桶
 * @author 黄志强
 * @data 2018/12/6 11:00
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RateLimitGatewayFilter implements GatewayFilter {

    /**
     * 桶的最大容量
     */
    int capacity;
    /**
     * 令牌 的补充量
     */
    int refillTokens;

    /**
     * 补充令牌的时间间隔
     */
    Duration refillDuration;

    private static final Map<String,Bucket> cache=new ConcurrentHashMap<>();

    private Bucket createBucket(){
        //
        Refill refill  = Refill.of(refillTokens, refillDuration);
        Bandwidth limit = Bandwidth.classic(capacity, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = cache.computeIfAbsent(ip, k -> createBucket());
        LOGGER.debug("ip:"+ip+"TokenBucket Available Tokens: " + bucket.getAvailableTokens());
        if (bucket.tryConsume(1)){
            return chain.filter(exchange);
        }else {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
    }
}
