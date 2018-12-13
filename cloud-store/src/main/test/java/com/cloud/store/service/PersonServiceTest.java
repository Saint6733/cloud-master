package com.cloud.store.service;

import com.cloud.store.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 类名称:PersonServiceTest
 * 类描述:接口服务测试类
 * @author shiqianghui
 * @date 2018-12-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
    @Autowired
    PersonService personService;

    /**
     * 查询数据量
     */
    @Test
    public void count() {
        Long count = personService.count();
        System.out.println(count);
    }

    /**
     * 查询数据量
     */
    @Test
    public void countByExample() {
        Person person = new Person();
        person.setId(1L);
        Long count = personService.countByExample(person);
        System.out.println(count);
    }

    /**
     * 查询第1个符合条件的数据
     */
    @Test
    public void findOneByExample() {
        Person person = new Person();
        Optional<Person> optional = personService.findOneByExample(person);
        if (optional.isPresent()) {
            Person person1 = optional.get();
            System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
        }
    }

    /**
     * 查询全部数据
     */
    @Test
    public void findAll() {
        List<Person> list = personService.findAll();
        if (!list.isEmpty()) {
            list.forEach(person -> {
                System.out.println("-----------------");
                System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getAge());
            });
        }
    }

    /**
     * 查询全部数据,排序
     */
    @Test
    public void findAllOfSort() {
        String sortType = "desc";
        String sortName = "id";
        List<Person> list = personService.findAllOfSort(sortType, sortName);
        if (!list.isEmpty()) {
            list.forEach(person -> {
                System.out.println("-----------------");
                System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getAge());
            });
        }
    }

    /**
     * 根据条件查询数据
     */
    @Test
    public void findAllByExample() {
        Person person = new Person();
        List<Person> list = personService.findAllByExample(person);
        if (!list.isEmpty()) {
            list.forEach(person1 -> {
                System.out.println("-----------------");
                System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
            });
        }
    }

    /**
     * 根据条件查询数据,排序
     */
    @Test
    public void findAllOfSrtByExample() {
        Person person = new Person();
        String sortType = "desc";
        String sortName = "id";
        List<Person> list = personService.findAllOfSrtByExample(person, sortType, sortName);
        if (!list.isEmpty()) {
            list.forEach(person1 -> {
                System.out.println("-----------------");
                System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
            });
        }
    }

    /**
     * 查询全部数据,分页
     */
    @Test
    public void findAllOfPage() {
        Integer page = 0;
        Integer size = 10;
        Page<Person> personPage = personService.findAllOfPage(page, size);
        if (!personPage.isEmpty()) {
            personPage.getContent().forEach( person -> {
                System.out.println("-----------------");
                System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getAge());
            });
        }
    }

    /**
     * 根据条件查询数据,分页
     */
    @Test
    public void findAllOfPageByExample() {
        Integer page = 0;
        Integer size = 10;
        Person person = new Person();
        Page<Person> personPage = personService.findAllOfPageByExample(person, page, size);
        if (!personPage.isEmpty()) {
            personPage.getContent().forEach( person1 -> {
                System.out.println("-----------------");
                System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
            });
        }
    }

    /**
     * 根据条件查询数据,排序,分页
     */
    @Test
    public void findAllOfSortAndPageByExample() {
        String sortType = "asc";
        String sortName = "id";
        Integer page = 0;
        Integer size = 10;
        Person person = new Person();
        Page<Person> personPage = personService.findAllOfSortAndPageByExample(person, sortType, sortName, page, size);
        if (!personPage.isEmpty()) {
            personPage.getContent().forEach( person1 -> {
                System.out.println("-----------------");
                System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
            });
        }
    }

    /**
     * 根据id查询数据是否存在
     */
    @Test
    public void existsById() {
        Long id = 1L;
        Boolean flag = personService.existsById(id);
        System.out.println(flag);
    }

    /**
     * 根据条件查询数据是否存在
     */
    @Test
    public void exists() {
        Person person = new Person();
        person.setName("tom");
        Boolean flag = personService.exists(person);
        System.out.println(flag);
    }

    /**
     * 添加数据
     */
    @Test
    public void save() {
        Person person = new Person();
        person.setId(1L);
        person.setName("tomm");
        person.setAge(22);
        Person person1 = personService.save(person);
        System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
    }

    /**
     * 批量添加数据
     */
    @Test
    public void saveAll() {
        List<Person> list = new ArrayList<>(2);
        Person person1 = new Person();
        person1.setId(7L);
        person1.setName("Lucy");
        person1.setAge(21);
        list.add(person1);

        Person person2 = new Person();
        person2.setId(8L);
        person2.setName("Lili");
        person2.setAge(21);
        list.add(person2);
        List<Person> list1 = personService.saveAll(list);
        if (!list1.isEmpty()) {
            list1.forEach(person -> {
                System.out.println("-----------------");
                System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getAge());
            });
        }
    }

    /**
     * 添加数据
     */
    @Test
    public void insert() {
        Person person = new Person();
        person.setId(9L);
        person.setName("jams");
        person.setAge(28);
        Person person1 = personService.insert(person);
        System.out.println(person1.getId() + "\t" + person1.getName() + "\t" + person1.getAge());
    }

    /**
     * 批量添加数据
     */
    @Test
    public void insertAll() {
        List<Person> list = new ArrayList<>(2);
        Person person1 = new Person();
        person1.setId(10L);
        person1.setName("bush");
        person1.setAge(50);
        list.add(person1);

        Person person2 = new Person();
        person2.setId(11L);
        person2.setName("gate");
        person2.setAge(51);
        list.add(person2);
        List<Person> list1 = personService.insert(list);
        if (!list1.isEmpty()) {
            list1.forEach(person -> {
                System.out.println("-----------------");
                System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getAge());
            });
        }
    }

    /**
     * 删除数据
     */
    @Test
    public void delete() {
        Person person = new Person();
        person.setId(11L);
        person.setName("gate");
        person.setAge(51);
        personService.delete(person);
    }

    /**
     * 删除全部数据
     */
    @Test
    public void deleteAll() {
        personService.deleteAll();
    }

    /**
     * 删除指定集合数据
     */
    @Test
    public void deleteAllOfList() {
        List<Person> list = new ArrayList<>(2);
        Person person1 = new Person();
        person1.setId(10L);
        person1.setName("bush");
        person1.setAge(50);
        list.add(person1);

        Person person2 = new Person();
        person2.setId(11L);
        person2.setName("gate");
        person2.setAge(51);
        list.add(person2);
        personService.deleteAll(list);
    }

    /**
     * 根据id删除数据
     */
    @Test
    public void deleteById() {
        Long id = 9L;
        personService.deleteById(id);
    }
}
