package com.cloud.gateway.service;


import com.alibaba.fastjson.JSON;
import com.cloud.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.util.*;

/**
 * @author 黄志强
 * @data 2018/12/10 9:46
 */
@Service
@Slf4j
public class GatewayService implements ApplicationEventPublisherAware{

    public static final String GATEWAY_ROUTES = "geteway_routes";
    private ApplicationEventPublisher publisher;
    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    //调用这个方法通知gateway做配置刷新
    private void notifyChanged() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }


    @Autowired
    private  RedisUtil redisUtil;


    /*@PostConstruct
    public void route(){
        //路由id和uri
        RouteDefinition definition=new RouteDefinition();
        definition.setId("cloud-store");
        URI uri = UriComponentsBuilder.fromUriString("lb://cloud-store").build().toUri();
        definition.setUri(uri);

        //断言
        PredicateDefinition predicateDefinition=new PredicateDefinition();
        predicateDefinition.setName("Path");
        Map<String,String> predicateValue=new HashMap<>(1);
        predicateValue.put("pattern","/store/**");
        predicateDefinition.setArgs(predicateValue);
        //过滤器
        FilterDefinition filterOne=new FilterDefinition();
        filterOne.setName("AddResponseHeader");
        Map<String,String> filterOneParam=new HashMap<>(2);
        filterOneParam.put("_genkey_0","X-Response-Default-Foo");
        filterOneParam.put("_genkey_1","Default-Bar");
        filterOne.setArgs(filterOneParam);

        FilterDefinition filterTwo=new FilterDefinition();
        filterTwo.setName("StripPrefix");
        Map<String,String> filterTwoParam=new HashMap<>(2);
        filterTwoParam.put("_genkey_0","1");
        filterTwo.setArgs(filterTwoParam);



        definition.setFilters(Arrays.asList(filterOne,filterTwo));
        definition.setPredicates((Arrays.asList(predicateDefinition)));
        LOGGER.debug("definition：----------------"+JSON.toJSONString(definition));

        //把路由信息放到redis中
        redisUtil.setHashItem(GATEWAY_ROUTES,"cloud-store",JSON.toJSONString(definition));

    }*/
    /**
     * 增加路由
     *
     */
    public String add(RouteDefinition definition) {
        Disposable subscribe = routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        if (subscribe.isDisposed()){
            redisUtil.setHashItem(GATEWAY_ROUTES,definition.getId(),JSON.toJSONString(definition));
        }
        notifyChanged();
        return "success";
    }


    /**
     * 更新路由
     */
    public String update(RouteDefinition definition) {
        try {
            Mono<Void> delete = this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
            redisUtil.removeHashItem(GATEWAY_ROUTES,definition.getId());
        } catch (Exception e) {
            return "update fail,not find route  routeId: " + definition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            redisUtil.setHashItem(GATEWAY_ROUTES,definition.getId(),JSON.toJSONString(definition));
            notifyChanged();
            return "success";
        } catch (Exception e) {
            return "update route  fail";
        }


    }

    /**
     * 删除路由
     *
     */
    public String delete(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
            redisUtil.removeHashItem(GATEWAY_ROUTES,id);
            notifyChanged();
            return "delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete fail";
        }

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


}
