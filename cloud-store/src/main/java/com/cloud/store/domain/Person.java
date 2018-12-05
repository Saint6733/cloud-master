package com.cloud.store.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 类名称:Person
 * 类描述:测试实体类
 * @author shiqianghui
 * @date 2018-12-05
 */
@Data
public class Person {
    @Id
    private Long id;

    private String name;

    private Integer age;
}
