package com.cloud.store.service;

import com.cloud.store.domain.Person;
import org.bson.Document;
import org.springframework.data.mongodb.core.index.IndexInfo;

import java.util.Collection;
import java.util.List;

/**
 * 类名称:MongodbTemplateService
 * 类描述:MongodbTemplate服务接口
 * @author shiqianghui
 * @date 2018-12-06
 */
public interface MongodbTemplateService {

    /**
     * 根据条件查询
     * @param person {@link Person}
     * @return {@link List< Person >}
     */
    List<Person> findByCriteria(Person person);

    /**
     * 根据条件和集合名查询
     * @param person {@link Person}
     * @param collectionName 集合名(mongo表名)
     * @return {@link List< Person >}
     */
    List<Person> findByCriteriaAndCollectionName(Person person, String collectionName);

    /**
     * 根据类名查询
     * @param clazz 类名
     * @return {@link List< Person >}
     */
    List<Person> findAllByClass(Class clazz);

    /**
     * 条件查询第1条符合条件的数据
     * @param person {@link Person}
     * @param clazz {@link Class}
     * @return {@link Person}
     */
    Person findOne(Person person, Class clazz);

    /**
     * 条件查询第1条符合条件的数据
     * @param person {@link Person}
     * @param clazz {@link Class}
     * @param collectionName 集合名(mongo表名)
     * @return {@link Person}
     */
    Person findOne(Person person, Class clazz, String collectionName);

    /**
     * 条件查询数据量
     * @param person {@link Person}
     * @return 数据量
     */
    Long countByCriteria(Person person);

    /**
     * 条件和集合名查询数据量
     * @param person {@link Person}
     * @param collectionName 集合名(mongo表名)
     * @return 数据量
     */
    Long countByCriteriaAndCollectionName(Person person, String collectionName);

    /**
     * 条件、类名和集合名查询数据量
     * @param person {@link Person}
     * @param clazz 类名
     * @param collectionName 集合名(mongo表名)
     * @return 数据量
     */
    Long countByCriteriaAndClassAndCollectionName(Person person, Class clazz, String collectionName);

    /**
     * 查询数据是否存在
     * @param person {@link Person}
     * @param clazz 类名
     * @return 是否存在
     */
    Boolean exists(Person person, Class clazz);

    /**
     * 增加数据
     * @param person {@link Person}
     * @return {@link Person}
     */
    Person insert(Person person);

    /**
     * 增加数据
     * @param person {@link Person}
     * @param collectionName 集合名(mongo表名)
     * @return {@link Person}
     */
    Person insert(Person person, String collectionName);

    /**
     * 批量增加数据
     * @param collection {@link Collection< Person >}
     * @param clazz {@link Class}
     * @return {@link Collection< Person >}
     */
    Collection<Person> insert(Collection<Person> collection, Class clazz);

    /**
     * 批量增加数据
     * @param collection {@link Collection< Person >}
     * @param collectionName 集合名(mongo表名)
     * @return {@link Collection< Person >}
     */
    Collection<Person> insert(Collection<Person> collection, String collectionName);

    /**
     * 批量增加数据
     * @param collection {@link Collection< Person >}
     * @param clazz {@link Class}
     * @return 本次新增数据量
     */
    Integer insertByExecutableInsert(Collection<Person> collection, Class clazz);

    /**
     * 批量增加数据
     * @param objectsToSave {@link Collection< Person >}
     * @return {@link Collection< Person >}
     */
    Collection<Person> insertAll(Collection<Person> objectsToSave);

    /**
     * 查询索引
     * @param clazz 类
     * @return 索引集
     */
    List<IndexInfo> listIndexInfos(Class clazz);

    /**
     * 查询索引
     * @param collectionName 集合名(mongo表名)
     * @return 索引集
     */
    List<IndexInfo> listIndexInfos(String collectionName);

    /**
     * 删除索引
     * @param clazz 类
     * @param indexName 索引名
     */
    void dropIndex(Class clazz, String indexName);

    /**
     * 删除索引
     * @param collectionName 集合名(mongo表名)
     * @param indexName 索引名
     */
    void dropIndex(String collectionName, String indexName);

    /**
     * 删除集合
     * @param clazz 类
     */
    void dropCollection(Class clazz);

    /**
     * 删除集合
     * @param collectionName 集合名(mongo表名)
     */
    void dropCollection(String collectionName);

    /**
     * 聚合统计各个年龄的人数
     * @param collectionName 集合名(mongo表名)
     * @return 聚合结果
     */
    List<Document> aggregateCountAge(String collectionName);

    /**
     * 聚合统计指定名称，年龄在gteAge和lteAge的数据
     * @param collectionName 集合名(mongo表名)
     * @param name 名称
     * @param gteAge 大于等于年龄
     * @param lteAge 小于等于年龄
     * @return 聚合结果
     */
    List<Document> aggregateCountByNameAndAge(String collectionName, String name, Integer gteAge, Integer lteAge);
}
