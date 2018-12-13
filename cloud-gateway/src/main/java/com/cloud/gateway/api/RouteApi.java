package com.cloud.gateway.api;

import com.cloud.common.Response.BaseResponse;
import com.cloud.gateway.domain.GatewayFilterDefinition;
import com.cloud.gateway.domain.GatewayPredicateDefinition;
import com.cloud.gateway.domain.GatewayRouteDefinition;
import com.cloud.gateway.service.GatewayService;
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
    public BaseResponse add(@RequestBody GatewayRouteDefinition gatewayRouteDefinition){
        BaseResponse result;
        try {

            RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
            String add = gatewayService.add(definition);
            result=new BaseResponse("10102000","新增路由成功",add);
            result.setSuccess(Boolean.TRUE);

        }catch (Exception e){
            result=new BaseResponse("10104000","路由新增失败",null);
            result.setSuccess(Boolean.FALSE);
        }
        return result;
    }
    @RequestMapping(value = "/route/definition",method = RequestMethod.PUT)
    public String update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition){
        RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
        return  gatewayService.update(definition);
    }

    @RequestMapping(value = "/route/definition/{id}",method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable String id){
        BaseResponse result;
        try {
            String delete = gatewayService.delete(id);
            result=new BaseResponse("10102000","删除成功",delete);
            result.setSuccess(Boolean.TRUE);
        }catch (Exception e){
            result=new BaseResponse("10104000","删除是啊比",null);
            result.setSuccess(Boolean.FALSE);
        }
       return result;
    }

    @GetMapping("/hh")
    public String s(){
        return "123";
    }

    /**
     * 获取路由配置的属性
     * @param gwdefinition
     * @return
     */
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
