package com.cloud.gateway.api;

import com.cloud.gateway.domain.GatewayFilterDefinition;
import com.cloud.gateway.domain.GatewayPredicateDefinition;
import com.cloud.gateway.domain.GatewayRouteDefinition;
import com.cloud.gateway.service.GatewayService;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄志强
 * @data 2018/12/11 14:52
 */
@RestController
public class RouteApi {


    private final GatewayService gatewayService;

    public RouteApi(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }
    @RequestMapping(value = "/route/definition",method = RequestMethod.POST)
    public String add(@RequestBody GatewayRouteDefinition gatewayRouteDefinition){
        try {

            RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
            String add = gatewayService.add(definition);
            return "添加成功";
        }catch (Exception e){
            return "添加失败";
        }


    }
    @RequestMapping(value = "/route/definition",method = RequestMethod.PUT)
    public String update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition){
        RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
        return  gatewayService.update(definition);
    }

    @RequestMapping(value = "/route/definition/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable String id){
        String delete = gatewayService.delete(id);
        return delete;
    }

    @GetMapping("/hh")
    public String s(){
        return "123";
    }
    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition) {
        RouteDefinition definition = new RouteDefinition();
        List<PredicateDefinition> pdList=new ArrayList<>();
        List<FilterDefinition> fdList=new ArrayList<>();
        definition.setId(gwdefinition.getId());
        //获取断言的集合
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList=gwdefinition.getPredicates();
        for (GatewayPredicateDefinition gpDefinition: gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        //获取过滤器的集合
        List<GatewayFilterDefinition> gfList=gwdefinition.getFilters();
        for (GatewayFilterDefinition gfDefinition : gfList) {
            FilterDefinition filterDefinition=new FilterDefinition();
            filterDefinition.setArgs(gfDefinition.getArgs());
            filterDefinition.setName(gfDefinition.getName());
            fdList.add(filterDefinition);
        }

        definition.setPredicates(pdList);
        definition.setFilters(fdList);
        URI uri = UriComponentsBuilder.fromUriString(gwdefinition.getUri()).build().toUri();
        definition.setUri(uri);
        return definition;
    }
}
