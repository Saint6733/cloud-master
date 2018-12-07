package com.cloud.stream.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * 类名称：KafkaSender<br>
 * 类描述：消息生产者<br>
 * 创建时间：2018年12月03日<br>
 *
 * @author jwg
 * @version 1.0.0
 */
@Component
@EnableBinding({Source.class,MyBinding.class})
public class KafkaSender {
    private final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private Source source;

    @Autowired
    private MyBinding myBinding;

    public void sendMessage1(String message) {
        try {
            source.output1().send(MessageBuilder.withPayload("消息: " + message).build());
            System.out.println("消息发送成功！" + message);
        } catch (Exception e) {
            logger.info("消息发送失败，原因："+e);
            e.printStackTrace();
        }
    }

    public void sendMessage2(Person person) {
        try {
            source.output1().send(MessageBuilder.withPayload(person).build());
            System.out.println("消息发送成功！");
        } catch (Exception e) {
            logger.info("消息发送失败，原因："+e);
            e.printStackTrace();
        }
    }

    public void sendMessage3(String message) {
        try {
            myBinding.output2().send(MessageBuilder.withPayload("消息: " + message)
                    .setHeader("name","测试")
                    .setHeader("flag", "aa")
                    .build());
            System.out.println("消息发送成功！" + message);
        } catch (Exception e) {
            logger.info("消息发送失败，原因："+e);
            e.printStackTrace();
        }
    }

}
