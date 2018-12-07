package com.cloud.store.service.impl;

import com.cloud.store.dao.PersonDao;
import com.cloud.store.domain.Person;
import com.cloud.store.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 类名称:PersonServiceImpl
 * 类描述:接口服务实现类
 * @author shiqianghui
 * @date 2018-12-05
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    /**
     * 查询数据量
     * @return {@link Long}
     */
    @Override
    public Long count() {
        return personDao.count();
    }

    /**
     * 查询数据量
     * @param person {@link Person}
     * @return {@link Long}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Long countByExample(Person person) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true)
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example example = Example.of(person, exampleMatcher);
        return personDao.count(example);
    }

    /**
     * 查询第1个符合条件的数据
     * @param person {@link Person}
     * @return {@link Optional<Person>}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Optional<Person> findOneByExample(Person person) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true)
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example example = Example.of(person, exampleMatcher);
        return personDao.findOne(example);
    }

    /**
     * 查询全部数据
     * @return {@link List<Person>}
     */
    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    /**
     * 查询全部数据,排序
     * @param sortType 排序类型(ASC:升序; DESC:降序)
     * @param sortName 排序字段
     * @return {@link List<Person>}
     */
    @Override
    public List<Person> findAllOfSort(String sortType, String sortName) {
        Sort.Direction direction;
        if (Sort.Direction.ASC.toString().equalsIgnoreCase(sortType)) {
            direction = Sort.Direction.ASC;
        } else if (Sort.Direction.DESC.toString().equalsIgnoreCase(sortType)) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }
        Sort sort = Sort.by(direction, sortName);
        return personDao.findAll(sort);
    }

    /**
     * 根据条件查询数据
     * @param person {@link Person}
     * @return {@link List<Person>}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> findAllByExample(Person person) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example example = Example.of(person, exampleMatcher);
        return personDao.findAll(example);
    }

    /**
     * 根据条件查询数据,排序
     * @param person {@link Person}
     * @param sortType 培训类型(asc:升序; desc:降序)
     * @param sortName 排序字段
     * @return {@link List<Person>}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> findAllOfSrtByExample(Person person, String sortType, String sortName) {
        Sort.Direction direction;
        if (Sort.Direction.ASC.toString().equalsIgnoreCase(sortType)) {
            direction = Sort.Direction.ASC;
        } else if (Sort.Direction.DESC.toString().equalsIgnoreCase(sortType)) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }
        Sort sort = Sort.by(direction, sortName);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example example = Example.of(person, exampleMatcher);
        return personDao.findAll(example, sort);
    }

    /**
     * 查询全部数据,分页
     * @return {@link Page<Person>}
     */
    @Override
    public Page<Person> findAllOfPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return personDao.findAll(pageable);
    }

    /**
     * 根据条件查询数据,分页
     * @param person {@link Person}
     * @param page 页号
     * @param size 页大小
     * @return {@link Page<Person>}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page<Person> findAllOfPageByExample(Person person, Integer page, Integer size) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example example = Example.of(person, exampleMatcher);
        Pageable pageable = PageRequest.of(page, size);
        return personDao.findAll(example, pageable);
    }

    /**
     * 根据条件查询数据,排序,分页
     * @param person {@link Person}
     * @param sortType 排序类型(ASC:升序; DESC:降序)
     * @param sortName 排序字段
     * @param page 页号
     * @param size 页大小
     * @return {@link Page<Person>}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page<Person> findAllOfSortAndPageByExample(Person person, String sortType, String sortName, Integer page, Integer size) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example example = Example.of(person, exampleMatcher);
        Sort.Direction direction;
        if (Sort.Direction.ASC.toString().equalsIgnoreCase(sortType)) {
            direction = Sort.Direction.ASC;
        } else if (Sort.Direction.DESC.toString().equalsIgnoreCase(sortType)) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }
        Sort sort = Sort.by(direction, sortName);
        Pageable pageable = PageRequest.of(page, size, sort);
        return personDao.findAll(example, pageable);
    }

    /**
     * 根据id查询数据是否存在
     * @param id {@link Long}
     * @return {@link Boolean}
     */
    @Override
    public Boolean existsById(Long id) {
        return personDao.existsById(id);
    }

    /**
     * 根据条件查询数据是否存在
     * @param person {@link Person}
     * @return {@link Boolean}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Boolean exists(Person person) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example example = Example.of(person, exampleMatcher);
        return personDao.exists(example);
    }

    /**
     * 添加数据
     * @param person {@link Person}
     * @return {@link Person}
     */
    @Override
    public Person save(Person person) {
        return personDao.save(person);
    }

    /**
     * 批量添加数据
     * @param list {@link List<Person>}
     * @return {@link List<Person>}
     */
    @Override
    public List<Person> saveAll(List<Person> list) {
        return personDao.saveAll(list);
    }

    /**
     * 添加数据
     * @param person {@link Person}
     * @return {@link Person}
     */
    @Override
    public Person insert(Person person) {
        return personDao.insert(person);
    }

    /**
     * 批量添加数据
     * @param list {{@link List<Person>}
     * @return {@link List<Person>}
     */
    @Override
    public List<Person> insert(List<Person> list) {
        return personDao.insert(list);
    }

    /**
     * 删除数据
     * @param person {@link Person}
     */
    @Override
    public void delete(Person person) {
        personDao.delete(person);
    }

    /**
     * 删除全部数据
     */
    @Override
    public void deleteAll() {
        personDao.deleteAll();
    }

    /**
     * 删除指定集合数据
     * @param list {@link List<Person>}
     */
    @Override
    public void deleteAll(List<Person> list) {
        personDao.deleteAll();
    }

    /**
     * 根据id删除数据
     * @param id {@link Long}
     */
    @Override
    public void deleteById(Long id) {
        personDao.deleteById(id);
    }
}
