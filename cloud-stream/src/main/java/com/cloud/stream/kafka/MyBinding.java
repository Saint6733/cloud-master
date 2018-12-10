package com.cloud.stream.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * 类名称：MyBinding<br>
 * 类描述：<br>
 * 创建时间：2018年12月06日<br>
 *
 * @author jwg
 * @version 1.0.0
 */
@Component
public interface MyBinding{
    //接收队列2
    String INPUT_2 = "testb";

    //发送队列2
    String OUTPUT_2 = "sourceB";

    @Input(MyBinding.INPUT_2)
    MessageChannel input2();

    @Output(MyBinding.OUTPUT_2)
    MessageChannel output2();
}
