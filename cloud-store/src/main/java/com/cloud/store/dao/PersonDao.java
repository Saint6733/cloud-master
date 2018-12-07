package com.cloud.store.dao;

import com.cloud.store.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 类名称:PersonDao
 * 类描述:数据访问对象类
 * @author shiqianghui
 * @date 2018-12-05
 */
public interface PersonDao extends MongoRepository<Person, Long> {
}
