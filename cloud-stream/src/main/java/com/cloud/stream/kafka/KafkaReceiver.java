package com.cloud.stream.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 类名称：KafkaReceiver<br>
 * 类描述：消息消费方<br>
 * 创建时间：2018年12月03日<br>
 *
 * @author jwg
 * @version 1.0.0
 */
@Component
@EnableBinding(Sink.class)
public class KafkaReceiver {
    private final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @StreamListener(Sink.INPUT_1)
    private void receive(String vote) {
        logger.info("接收到消息 : " + vote);
        System.out.println("接收到消息：" + vote);
    }
}
