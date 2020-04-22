package com.david.consumer;

import com.david.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PersistentQueueReceiver {

    @RabbitListener(queues = QueueConfig.PERSISTENT_QUEUE)
    public void receive(String msg) throws IOException {
        System.out.println("[消费者] 持久化消息队列 => " + msg);
    }

}
