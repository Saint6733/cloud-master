package com.cloud.stream.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类名称：SendTest<br>
 * 类描述：消息发送测试类<br>
 * 创建时间：2018年12月04日<br>
 *
 * @author jwg
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {

    @Autowired
    private KafkaSender kafkaSender;

    @Test
    public void test1(){
        kafkaSender.sendMessage1("这是一条测试消息!");
    }

    @Test
    public void test2(){
        Person person = Person.builder().name("张三").age(22).build();
        kafkaSender.sendMessage2(person);
    }

    @Test
    public void test3(){
        for (int i = 0; i<10; i++){
            kafkaSender.sendMessage1("asdfghj" + i);
        }
    }

    @Test
    public void test4(){
        kafkaSender.sendMessage3("这是一条分组测试消息!");
    }
}
