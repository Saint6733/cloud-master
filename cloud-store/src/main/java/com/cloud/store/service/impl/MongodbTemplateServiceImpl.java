package com.cloud.store.service.impl;

import com.cloud.store.domain.Person;
import com.cloud.store.service.MongodbTemplateService;
import com.mongodb.bulk.BulkWriteResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ExecutableInsertOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * 类名称:MongodbTemplateServiceImpl
 * 类描述:MongodbTemplate服务接口实现类
 * @author shiqianghui
 * @date 2018-12-06
 */
@Slf4j
@Service
public class MongodbTemplateServiceImpl implements MongodbTemplateService {

    private final MongoTemplate mongodbTemplate;

    public MongodbTemplateServiceImpl(MongoTemplate mongodbTemplate) {
        this.mongodbTemplate = mongodbTemplate;
    }

    /**
     * 根据条件查询
     * @param person {@link Person}
     * @return {@link List< Person >}
     */
    @Override
    public List<Person> findByCriteria(Person person) {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        if (person.getId() != null) {
            criteria.andOperator(Criteria.where("id").is(person.getId()));
        }
        if (!StringUtils.isEmpty(person.getName())) {
            criteria.andOperator(Criteria.where("name").is(person.getName()));
        }
        if (person.getAge() != null) {
            criteria.andOperator(Criteria.where("age").is(person.getAge()));
        }
        return mongodbTemplate.find(query, Person.class);
    }

    /**
     * 根据条件和集合名查询
     * @param person {@link Person}
     * @param collectionName 集合名(mongo表名)
     * @return {@link List< Person >}
     */
    @Override
    public List<Person> findByCriteriaAndCollectionName(Person person, String collectionName) {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        if (person.getId() != null) {
            criteria.andOperator(Criteria.where("id").is(person.getId()));
        }
        if (!StringUtils.isEmpty(person.getName())) {
            criteria.andOperator(Criteria.where("name").is(person.getName()));
        }
        if (person.getAge() != null) {
            criteria.andOperator(Criteria.where("age").is(person.getAge()));
        }
        return mongodbTemplate.find(query, Person.class, collectionName);
    }

    /**
     * 根据类名查询
     * @param clazz 类名
     * @return {@link List< Person >}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> findAllByClass(Class clazz) {
        return mongodbTemplate.findAll(clazz);
    }

    /**
     * 条件查询第1条符合条件的数据
     * @param person {@link Person}
     * @param clazz {@link Class}
     * @return {@link Person}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Person findOne(Person person, Class clazz) {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        if (person.getId() != null) {
            criteria.andOperator(Criteria.where("id").is(person.getId()));
        }
        if (!StringUtils.isEmpty(person.getName())) {
            criteria.andOperator(Criteria.where("name").is(person.getName()));
        }
        if (person.getAge() != null) {
            criteria.andOperator(Criteria.where("age").is(person.getAge()));
        }
        return (Person)mongodbTemplate.findOne(query, clazz);
    }

    /**
     * 条件查询第1条符合条件的数据
     * @param person {@link Person}
     * @param clazz {@link Class}
     * @param collectionName 集合名(mongo表名)
     * @return {@link Person}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Person findOne(Person person, Class clazz, String collectionName) {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        if (person.getId() != null) {
            criteria.andOperator(Criteria.where("id").is(person.getId()));
        }
        if (!StringUtils.isEmpty(person.getName())) {
            criteria.andOperator(Criteria.where("name").is(person.getName()));
        }
        if (person.getAge() != null) {
            criteria.andOperator(Criteria.where("age").is(person.getAge()));
        }
        return (Person)mongodbTemplate.findOne(query, clazz, collectionName);
    }

    /**
     * 条件查询数据量
     * @param person {@link Person}
     * @return 数据量
     */
    @Override
    public Long countByCriteria(Person person) {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        if (person.getId() != null) {
            criteria.andOperator(Criteria.where("id").is(person.getId()));
        }
        if (!StringUtils.isEmpty(person.getName())) {
            criteria.andOperator(Criteria.where("name").is(person.getName()));
        }
        if (person.getAge() != null) {
            criteria.andOperator(Criteria.where("age").is(person.getAge()));
        }
        return mongodbTemplate.count(query, Person.class);
    }

    /**
     * 条件和集合名查询数据量
     * @param person {@link Person}
     * @param collectionName 集合名(mongo表名)
     * @return 数据量
     */
    @Override
    public Long countByCriteriaAndCollectionName(Person person, String collectionName) {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        if (person.getId() != null) {
            criteria.andOperator(Criteria.where("id").is(person.getId()));
        }
        if (!StringUtils.isEmpty(person.getName())) {
            criteria.andOperator(Criteria.where("name").is(person.getName()));
        }
        if (person.getAge() != null) {
            criteria.andOperator(Criteria.where("age").is(person.getAge()));
        }
        return mongodbTemplate.count(query, collectionName);
    }

    /**
     * 条件、类名和集合名查询数据量
     * @param person {@link Person}
     * @param clazz 类名
     * @param collectionName 集合名(mongo表名)
     * @return 数据量
     */
    @Override
    public Long countByCriteriaAndClassAndCollectionName(Person person, Class clazz, String collectionName) {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        if (person.getId() != null) {
            criteria.andOperator(Criteria.where("id").is(person.getId()));
        }
        if (!StringUtils.isEmpty(person.getName())) {
            criteria.andOperator(Criteria.where("name").is(person.getName()));
        }
        if (person.getAge() != null) {
            criteria.andOperator(Criteria.where("age").is(person.getAge()));
        }
        return mongodbTemplate.count(query, clazz, collectionName);
    }

    /**
     * 查询数据是否存在
     * @param person {@link Person}
     * @param clazz 类名
     * @return 是否存在
     */
    @Override
    public Boolean exists(Person person, Class clazz) {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        if (person.getId() != null) {
            criteria.andOperator(Criteria.where("id").is(person.getId()));
        }
        if (!StringUtils.isEmpty(person.getName())) {
            criteria.andOperator(Criteria.where("name").is(person.getName()));
        }
        if (person.getAge() != null) {
            criteria.andOperator(Criteria.where("age").is(person.getAge()));
        }
        return mongodbTemplate.exists(query, clazz);
    }

    /**
     * 新增数据
     * @param person {@link Person}
     * @return {@link Person}
     */
    @Override
    public Person insert(Person person) {
        return mongodbTemplate.insert(person);
    }

    /**
     * 新增数据
     * @param person {@link Person}
     * @param collectionName 集合名(mongo表名)
     * @return {@link Person}
     */
    @Override
    public Person insert(Person person, String collectionName) {
        return mongodbTemplate.insert(person, collectionName);
    }

    /**
     * 批量新增数据
     * @param collection {@link Collection< Person >}
     * @param clazz {@link Class}
     * @return {@link Collection< Person >}
     */
    @Override
    public Collection<Person> insert(Collection<Person> collection, Class clazz) {
        return mongodbTemplate.insert(collection, clazz);
    }

    /**
     * 批量增加数据
     * @param collection {@link Collection< Person >}
     * @param collectionName 集合名(mongo表名)
     * @return {@link Collection< Person >}
     */
    @Override
    public Collection<Person> insert(Collection<Person> collection, String collectionName) {
        return mongodbTemplate.insert(collection, collectionName);
    }

    /**
     * 批量新增数据
     * @param collection {@link Collection< Person >}
     * @param clazz {@link Class}
     * @return 本次新增数据量
     */
    @Override
    public Integer insertByExecutableInsert(Collection<Person> collection, Class clazz) {
        ExecutableInsertOperation.ExecutableInsert<Person> executableInsert = mongodbTemplate.insert(Person.class);
        BulkWriteResult bulkWriteResult = executableInsert.bulk(collection);
        return bulkWriteResult.getInsertedCount();
    }

    /**
     * 批量增加数据
     * @param objectsToSave {@link Collection< Person >}
     * @return {@link Collection< Person >}
     */
    @Override
    public Collection<Person> insertAll(Collection<Person> objectsToSave) {
        return mongodbTemplate.insertAll(objectsToSave);
    }

    /**
     * 查询索引
     * @param clazz 类
     * @return 索引集
     */
    @Override
    public List<IndexInfo> listIndexInfos(Class clazz) {
        IndexOperations indexOperations = mongodbTemplate.indexOps(Person.class);
        return indexOperations.getIndexInfo();
    }

    /**
     * 查询索引
     * @param collectionName 集合名(mongo表名)
     * @return 索引集
     */
    @Override
    public List<IndexInfo> listIndexInfos(String collectionName) {
        IndexOperations indexOperations = mongodbTemplate.indexOps(collectionName);
        return indexOperations.getIndexInfo();
    }

    /**
     * 删除索引
     * @param clazz 类
     * @param indexName 索引名
     */
    @Override
    public void dropIndex(Class clazz, String indexName) {
        IndexOperations indexOperations = mongodbTemplate.indexOps(clazz);
        indexOperations.dropIndex(indexName);
    }

    /**
     * 删除索引
     * @param collectionName 集合名(mongo表名)
     * @param indexName 索引名
     */
    @Override
    public void dropIndex(String collectionName, String indexName) {
        IndexOperations indexOperations = mongodbTemplate.indexOps(collectionName);
        indexOperations.dropIndex(indexName);
    }

    /**
     * 删除集合
     * @param clazz 类
     */
    @Override
    @SuppressWarnings("unchecked")
    public void dropCollection(Class clazz) {
        mongodbTemplate.dropCollection(clazz);
    }

    /**
     * 删除集合
     * @param collectionName 集合名(mongo表名)
     */
    @Override
    public void dropCollection(String collectionName) {
        mongodbTemplate.dropCollection(collectionName);
    }

    /**
     * 聚合统计各个年龄的人数
     * @return 聚合查询结果
     */
    @Override
    public List<Document> aggregateCountAge(String collectionName) {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group("age").count().as("人数"));
        return mongodbTemplate.aggregate(aggregation, collectionName, Document.class).getMappedResults();
    }

    /**
     * 聚合统计指定名称，年龄在gteAge和lteAge的数据
     * @param collectionName 集合名(mongo表名)
     * @param name 名称
     * @param gteAge 大于等于年龄
     * @param lteAge 小于等于年龄
     * @return 聚合结果
     */
    @Override
    public List<Document> aggregateCountByNameAndAge(String collectionName, String name, Integer gteAge, Integer lteAge) {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("name").is(name)
                        .orOperator(Criteria.where("age").gte(gteAge),
                                Criteria.where("age").lte(lteAge))),
                Aggregation.group("age").count().as("count"),
                Aggregation.sort(Sort.Direction.DESC, "count"));
        return mongodbTemplate.aggregate(aggregation, collectionName, Document.class).getMappedResults();
    }

}
