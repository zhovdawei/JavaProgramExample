package com.david.consumer;

import com.david.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class DelayQueueReceiver {

//    @RabbitListener(queues = QueueConfig.DELAY_QUEUE)
//    public void receiveDelay(String msg) throws IOException {
//        System.out.println("[延迟]消息队列 => " + msg+" : "+System.currentTimeMillis());
//    }

}
