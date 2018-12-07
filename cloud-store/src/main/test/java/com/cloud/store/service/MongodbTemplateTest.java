package com.cloud.store.service;

import com.cloud.store.domain.Person;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTemplateTest {

    @Autowired
    MongoTemplate mongodbTemplate;

    @Autowired
    MongodbTemplateService mongodbTemplateService;

    @Test
    public void query() {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        criteria.andOperator(Criteria.where("name").is("jane"));
        List<Person> list = mongodbTemplate.find(query, Person.class);
        if (!list.isEmpty()) {
            list.forEach(person -> {
                System.out.println("-----------------");
                System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getAge());
            });
        }
    }

    /**
     * 根据条件查询
     */
    @Test
    public void findByCriteria() {
        Person person = new Person();
        List<Person> list = mongodbTemplateService.findByCriteria(person);
        if (!list.isEmpty()) {
            list.forEach(person1 -> {
                System.out.println("-----------------");
                System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
            });
        }
    }

    /**
     * 根据条件和集合名查询
     */
    @Test
    public void findByCriteriaAndCollectionName() {
        Person person = new Person();
        String collectionName = "person";
        List<Person> list = mongodbTemplateService.findByCriteriaAndCollectionName(person, collectionName);
        if (!list.isEmpty()) {
            list.forEach(person1 -> {
                System.out.println("-----------------");
                System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
            });
        }
    }

    /**
     * 条件查询数据量
     */
    @Test
    public void countByCriteria() {
        Person person = new Person();
        Long count = mongodbTemplateService.countByCriteria(person);
        System.out.println(count);
    }

    /**
     * 条件和集合名查询数据量
     */
    @Test
    public void countByCriteriaAndCollectionName() {
        Person person = new Person();
        String collectionName = "person";
        Long count = mongodbTemplateService.countByCriteriaAndCollectionName(person, collectionName);
        System.out.println(count);
    }

    /**
     * 条件、类名和集合名查询数据量
     */
    @Test
    public void countByCriteriaAndClassAndCollectionName() {
        Person person = new Person();
        Class clazz = Person.class;
        String collectionName = "person";
        Long count = mongodbTemplateService.countByCriteriaAndClassAndCollectionName(person, clazz, collectionName);
        System.out.println(count);
    }

    /**
     * 条件查询第1条符合条件的数据
     */
    @Test
    public void findOne() {
        Person person = new Person();
        Class clazz = Person.class;
        Person person1 = mongodbTemplateService.findOne(person, clazz);
        System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
    }

    /**
     * 条件查询第1条符合条件的数据
     */
    @Test
    public void findOneByCollectionName() {
        Person person = new Person();
        Class clazz = Person.class;
        String collectionName = "person";
        Person person1 = mongodbTemplateService.findOne(person, clazz, collectionName);
        System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
    }

    /**
     * 根据类名查询
     */
    @Test
    public void findAllByClass() {
        List<Person> list = mongodbTemplateService.findAllByClass(Person.class);
        if (!list.isEmpty()) {
            list.forEach(person -> {
                System.out.println("------------");
                System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getAge());
            });
        }

    }

    /**
     * 查询数据是否存在
     */
    @Test
    public void exists() {
        Person person = new Person();
        Class clazz = Person.class;
        Boolean flag = mongodbTemplateService.exists(person, clazz);
        System.out.println(flag);
    }

    /**
     * 新增数据
     */
    @Test
    public void insert() {
        Person person = new Person();
        person.setId(12L);
        person.setName("tim");
        person.setAge(30);
        Person person1 = mongodbTemplateService.insert(person);
        System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
    }

    /**
     * 新增数据
     */
    @Test
    public void insertByCollectionName() {
        Person person = new Person();
        person.setId(13L);
        person.setName("kate");
        person.setAge(31);
        String collectionName = "person";
        Person person1 = mongodbTemplateService.insert(person, collectionName);
        System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
    }

    /**
     * 批量新增数据
     */
    @Test
    public void insertOfCollection() {
        List<Person> list = new ArrayList<>(2);
        Person person1 = new Person();
        person1.setId(14L);
        person1.setName("mark");
        person1.setAge(50);
        list.add(person1);

        Person person2 = new Person();
        person2.setId(15L);
        person2.setName("stone");
        person2.setAge(32);
        list.add(person2);
        Collection<Person> collection = mongodbTemplateService.insert(list, Person.class);
        printCollection(collection);
    }

    /**
     * 批量增加数据
     */
    @Test
    public void insertBatchByCollectionName() {
        List<Person> list = new ArrayList<>(2);
        Person person1 = new Person();
        person1.setId(18L);
        person1.setName("Everly");
        person1.setAge(20);
        list.add(person1);

        Person person2 = new Person();
        person2.setId(19L);
        person2.setName("Judith");
        person2.setAge(21);
        list.add(person2);
        Collection<Person> collection = mongodbTemplateService.insert(list, "person");
        printCollection(collection);
    }
    /**
     * 批量新增数据
     */
    @Test
    public void insertByExecutableInsert() {
        List<Person> list = new ArrayList<>(2);
        Person person1 = new Person();
        person1.setId(16L);
        person1.setName("Eira");
        person1.setAge(22);
        list.add(person1);

        Person person2 = new Person();
        person2.setId(17L);
        person2.setName("Ellen");
        person2.setAge(23);
        list.add(person2);
        Integer count = mongodbTemplateService.insertByExecutableInsert(list, Person.class);
        System.out.println(count);
    }

    /**
     * 批量增加数据
     */
    @Test
    public void insertAll() {
        List<Person> list = new ArrayList<>(2);
        Person person1 = new Person();
        person1.setId(23L);
        person1.setName("tom");
        person1.setAge(22);
        list.add(person1);

        Person person2 = new Person();
        person2.setId(24L);
        person2.setName("tom");
        person2.setAge(22);
        list.add(person2);
        Collection<Person> collection = mongodbTemplateService.insertAll(list);
        printCollection(collection);
    }

    /**
     * 查询索引
     */
    @Test
    public void listIndexInfos() {
        List<IndexInfo> list = mongodbTemplateService.listIndexInfos(Person.class);
        if (!list.isEmpty()) {
            list.forEach(indexInfo -> {
                System.out.println("-------------------");
                System.out.println(indexInfo.getName());
            });
        }
    }

    /**
     * 查询索引
     */
    @Test
    public void listIndexInfosByCollectionName() {
        String collectionName = "person";
        List<IndexInfo> list = mongodbTemplateService.listIndexInfos(collectionName);
        if (!list.isEmpty()) {
            list.forEach(indexInfo -> {
                System.out.println("-----------------");
                System.out.println(indexInfo.getName());
            });
        }
    }

    /**
     * 删除索引
     */
    @Test
    public void dropIndexByClass() {
        Class clazz = Person.class;
        String indexName = "_id_";
        mongodbTemplateService.dropIndex(clazz, indexName);
    }

    /**
     * 删除索引
     */
    @Test
    public void dropIndexByCollectionName() {
        String collectionName = "person";
        String indexName = "_id_";
        mongodbTemplateService.dropIndex(collectionName, indexName);
    }

    /**
     * 删除集合
     */
    @Test
    public void dropCollectionByClass() {
        Class clazz = Person.class;
        mongodbTemplateService.dropCollection(clazz);
    }

    /**
     * 删除集合
     */
    @Test
    public void dropCollectionByCollectionName() {
        String collectionName = "person";
        mongodbTemplateService.dropCollection(collectionName);
    }

    /**
     * 聚合统计各个年龄的人数
     */
    @Test
    public void aggregateCountAge() {
        String collectionName = "person";
        List<Document> list = mongodbTemplateService.aggregateCountAge(collectionName);
        if (!list.isEmpty()) {
            list.forEach(basicDBObject -> {
                System.out.println("------------------");
                System.out.println(basicDBObject.toJson());
            });
        }
    }

    /**
     * 聚合统计指定名称，年龄在gteAge和lteAge的数据
     */
    @Test
    public void aggregateCountByNameAndAge() {
        String collectionName = "person";
        String name = "tom";
        Integer gteAge = 10;
        Integer lteAge = 60;
        List<Document> list = mongodbTemplateService.aggregateCountByNameAndAge(collectionName, name, gteAge, lteAge);
        if (!list.isEmpty()) {
            list.forEach(basicDBObject -> {
                System.out.println("-----------------");
                System.out.println(basicDBObject.toJson());
            });
        }
    }

    private void printCollection(Collection<Person> collection) {
        if (!collection.isEmpty()) {
            Iterator<Person> iterator = collection.iterator();
            while (iterator.hasNext()) {
                Person person = iterator.next();
                System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getAge());
            }
        }
    }
}
