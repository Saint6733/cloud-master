package com.cloud.stream.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * 类名称：Sink<br>
 * 类描述：输入通道,并绑定输入通道配置信息<br>
 * 创建时间：2018年12月03日<br>
 *
 * @author jwg
 * @version 1.0.0
 */
public interface Sink {
    //接收队列1
    String INPUT_1 = "testa";

    @Input(Sink.INPUT_1)
    SubscribableChannel input1();

}
