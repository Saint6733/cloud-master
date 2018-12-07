package com.cloud.stream.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
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
@EnableBinding({Sink.class, MyBinding.class})
public class KafkaReceiver {
    private final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    /*@StreamListener(Sink.INPUT_1)
    @SendTo(MyBinding.OUTPUT_2)
    private String receive1(String vote) {
        logger.info("receive1接收到消息 : " + vote);
        System.out.println("receive1接收到消息：" + vote);
        return "中转消息：" + vote;
    }*/


    /**
     * 接收到receive1的中转消息
     * @param vote
     */
    /*@StreamListener(MyBinding.INPUT_2)
    private void receive2(String vote) {
        logger.info("receive2接收到消息 : " + vote);
        System.out.println("receive2接收到消息：" + vote);
    }*/

    @StreamListener(value = MyBinding.INPUT_2 , condition = "headers['flag']=='aa'")
    private void receive3(String vote) {
        logger.info("receive3接收到消息 : " + vote);
        System.out.println("receive3接收到消息：" + vote);
    }

    @StreamListener(value = Sink.INPUT_1 , condition = "headers['flag']=='bb'")
    private void receive4(String vote) {
        logger.info("receive4接收到消息 : " + vote);
        System.out.println("receive4接收到消息：" + vote);
    }

}
