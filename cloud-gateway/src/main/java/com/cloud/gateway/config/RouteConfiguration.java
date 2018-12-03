package com.cloud.gateway.config;

import com.cloud.gateway.filter.GlobalGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

/**
 * 类名称：RouteConfiguration<br>
 * 类描述：路由配置 <br>
 * 创建时间：2018年11月30日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@Configuration
public class RouteConfiguration {
	
	private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client";
	private static final String ALLOWED_METHODS = "*";
	private static final String ALLOWED_ORIGIN = "*";
	private static final String ALLOWED_EXPOSE = "*";
	private static final String MAX_AGE = "18000L";
	
	@Bean
	public WebFilter corsFilter() {
		return (ServerWebExchange ctx, WebFilterChain chain) -> {
			ServerHttpRequest request = ctx.getRequest();
			if (CorsUtils.isCorsRequest(request)) {
				ServerHttpResponse response = ctx.getResponse();
				HttpHeaders headers = response.getHeaders();
				headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
				headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
				headers.add("Access-Control-Max-Age", MAX_AGE);
				headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
				headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
				headers.add("Access-Control-Allow-Credentials", "true");
				if (request.getMethod() == HttpMethod.OPTIONS) {
					response.setStatusCode(HttpStatus.OK);
					return Mono.empty();
				}
			}
			return chain.filter(ctx);
		};
	}


	@Bean
	public RouteLocator streamRouteLocator(RouteLocatorBuilder locatorBuilder){
		return locatorBuilder.routes().route("cloud-stream",r->r.path("/stream/**")
                .filters(f->f.stripPrefix(1).prefixPath("/stream").addResponseHeader("X-Response-Foo","Bar"))
                .uri("lb://cloud-stream")
        ).build();
	}
    @Bean
	public RouteLocator adminRouteLocator(RouteLocatorBuilder locatorBuilder){
	    return locatorBuilder.routes().route("cloud-admin",r->r.path("/admin/**")
                .filters(f->f.stripPrefix(1).prefixPath("/admin").addResponseHeader("X-Response-Foo","Bar"))
                .uri("lb://cloud-admin")).build();
    }
    @Bean
    public RouteLocator sleuthRouteLocator(RouteLocatorBuilder locatorBuilder){
        return locatorBuilder.routes().route("cloud-sleuth",r->r.path("/sleuth/**")
                .filters(f->f.stripPrefix(1).prefixPath("/sleuth").addResponseHeader("X-Response-Foo","Bar"))
                .uri("lb://cloud-sleuth")).build();
    }

    @Bean
    public RouteLocator storeRouteLocator(RouteLocatorBuilder locatorBuilder){
        return locatorBuilder.routes().route("cloud-store",r->r.path("/store/**")
                .filters(f->f.stripPrefix(1).prefixPath("/store").addResponseHeader("X-Response-Foo","Bar"))
                .uri("lb://cloud-store")).build();
    }
}