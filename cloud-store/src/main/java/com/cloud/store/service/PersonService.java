package com.cloud.store.service;

import com.cloud.store.domain.Person;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * 类名称:PersonService
 * 类描述:服务接口
 * @author shiqianghui
 * @date 2018-12-05
 */
public interface PersonService {

    /**
     * 查询数据量
     * @return 数据量
     */
    Long count();

    /**
     * 查询数据量
     * @param person {@link Person}
     * @return 数据量
     */
    Long countByExample(Person person);

    /**
     * 查询第1个符合条件的数据
     * @param person {@link Person}
     * @return {@link Optional}
     */
    Optional<Person> findOneByExample(Person person);

    /**
     * 查询全部数据
     * @return {@link List}
     */
    List<Person> findAll();

    /**
     * 查询全部数据,排序
     * @param sortType 排序类型(asc:升序; desc:降序)
     * @param sortName 排序字段
     * @return {@link List<Person>}
     */
    List<Person> findAllOfSort(String sortType, String sortName);

    /**
     * 根据条件查询数据
     * @param person {@link Person}
     * @return {@link List<Person>}
     */
    List<Person> findAllByExample(Person person);

    /**
     * 根据条件查询数据,排序
     * @param person {@link Person}
     * @param sortType 排序类型(asc:升序; desc:降序)
     * @param sortName 排序字段
     * @return {@link List}
     */
    List<Person> findAllOfSrtByExample(Person person, String sortType, String sortName);

    /**
     * 查询全部数据,分页
     * @param page 页号
     * @param size 页大小
     * @return @link Page<Person>}
     */
    Page<Person> findAllOfPage(Integer page, Integer size);

    /**
     * 根据条件查询数据,分页
     * @param person {@link Person}
     * @param page 页号
     * @param size 页大小
     * @return {@link Page<Person>}
     */
    Page<Person> findAllOfPageByExample(Person person, Integer page, Integer size);

    /**
     * 根据条件查询数据,排序,分页
     * @param person {@link Person}
     * @param sortType 排序类型(asc:升序; desc:降序)
     * @param sortName 排序字段
     * @param page 页号
     * @param size 页大小
     * @return {@link Page<Person>}
     */
    Page<Person> findAllOfSortAndPageByExample(Person person, String sortType, String sortName, Integer page, Integer size);

    /**
     * 根据id查询数据是否存在
     * @param id {@link Long}
     * @return {@link Boolean}
     */
    Boolean existsById(Long id);

    /**
     * 根据条件查询数据是否存在
     * @param person {@link Person}
     * @return {@link Boolean}
     */
    Boolean exists(Person person);

    /**
     * 添加数据
     * @param person {@link Person}
     * @return {@link Person}
     */
    Person save(Person person);

    /**
     * 批量添加数据
     * @param list {@link List<Person>}
     * @return {@link List<Person>}
     */
    List<Person> saveAll(List<Person> list);

    /**
     * 添加数据
     * @param person {@link Person}
     * @return {@link Person}
     */
    Person insert(Person person);

    /**
     * 批量添加数据
     * @param list {{@link List<Person>}
     * @return {@link List<Person>}
     */
    List<Person> insert(List<Person> list);

    /**
     * 删除数据
     * @param person {@link Person}
     */
    void delete(Person person);

    /**
     * 删除全部数据
     */
    void deleteAll();

    /**
     * 删除指定集合数据
     * @param list {@link List<Person>}
     */
    void deleteAll(List<Person> list);

    /**
     * 根据id删除数据
     * @param id {@link Long}
     */
    void deleteById(Long id);
}
