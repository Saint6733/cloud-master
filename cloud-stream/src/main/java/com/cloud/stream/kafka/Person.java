package com.cloud.stream.kafka;

import lombok.Builder;
import lombok.Data;

/**
 * 类名称：Person<br>
 * 类描述：<br>
 * 创建时间：2018年12月05日<br>
 *
 * @author jwg
 * @version 1.0.0
 */
@Data
@Builder
public class Person {

    private String name;

    private Integer age;
}
