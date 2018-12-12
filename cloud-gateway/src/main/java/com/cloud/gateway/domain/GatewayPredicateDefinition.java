package com.cloud.gateway.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 黄志强
 * @data 2018/12/11 15:00
 */
@Data
public class GatewayPredicateDefinition {
    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言参数
     */
    private Map<String, String> args = new HashMap<>();
}
