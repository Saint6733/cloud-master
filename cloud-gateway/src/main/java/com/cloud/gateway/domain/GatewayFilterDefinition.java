package com.cloud.gateway.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄志强
 * @data 2018/12/11 15:02
 */
@Data
public class GatewayFilterDefinition {
    /**
     * 过滤器的名称
     */
    private String name;

    /**
     * 配置过滤器的参数
     */
    private Map<String, String> args = new HashMap<>();

}
