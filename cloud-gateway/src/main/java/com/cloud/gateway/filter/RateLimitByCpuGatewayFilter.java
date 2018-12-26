package com.cloud.gateway.filter;

import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author 黄志强
 * @data 2018/12/13 17:22
 */
@Component
public class RateLimitByCpuGatewayFilter implements GatewayFilter {

    /**
     * 可以根据这些参数来做限流
     */
    /*{
        "names": [
        "jvm.buffer.memory.used",
                "jvm.memory.used",
                "jvm.buffer.count",
                "jvm.gc.memory.allocated",
                "logback.events",
                "process.uptime",
                "jvm.memory.committed",
                "system.load.average.1m",
                "jvm.gc.pause",
                "jvm.gc.max.data.size",
                "jvm.buffer.total.capacity",
                "jvm.memory.max",
                "system.cpu.count",
                "system.cpu.usage",
                "process.files.max",
                "jvm.threads.daemon",
                "http.server.requests",
                "jvm.threads.live",
                "process.start.time",
                "jvm.classes.loaded",
                "jvm.classes.unloaded",
                "jvm.threads.peak",
                "jvm.gc.live.data.size",
                "jvm.gc.memory.promoted",
                "process.files.open",
                "process.cpu.usage"
  ]
    }*/

    private  final MetricsEndpoint metricsEndpoint;

    public RateLimitByCpuGatewayFilter(MetricsEndpoint metricsEndpoint) {
        this.metricsEndpoint = metricsEndpoint;
    }

    private static final String METRIC_NAME = "system.cpu.usage";
    private static final double MAX_USAGE = 0.50D;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        Double systemCpuUsage = metricsEndpoint.metric(METRIC_NAME, null).getMeasurements().stream()
                .filter(Objects::nonNull)
                .findFirst()
                .map(MetricsEndpoint.Sample::getValue)
                .filter(Double::isFinite)
                .orElse(0.0D);

        boolean ok = systemCpuUsage < MAX_USAGE;
        if (!ok) {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        } else {
            return chain.filter(exchange);
        }
    }
}
