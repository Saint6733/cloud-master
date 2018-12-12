package com.cloud.gateway.service;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import javax.annotation.Resource;

/**
 * 动态路由的实现
 * @author 黄志强
 * @data 2018/12/10 16:41
 */
public class DynamicRouteService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    /**
     调用这个方法通知gateway做配置刷新
     *
     */
    private void notifyChanged(){
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher=applicationEventPublisher;
    }
}
