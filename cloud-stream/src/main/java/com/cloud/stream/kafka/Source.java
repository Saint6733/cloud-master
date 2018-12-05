package com.cloud.stream.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * 类名称：Source<br>
 * 类描述：输出通道并绑定输出通道配置信息<br>
 * 创建时间：2018年12月03日<br>
 *
 * @author jwg
 * @version 1.0.0
 */
@Component
public interface Source {
    //发送队列1
    String OUTPUT_1 = "sourceA";

    @Output(Source.OUTPUT_1)
    MessageChannel output1();

}
