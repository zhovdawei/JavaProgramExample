package com.david.consumer;

import com.david.config.QueueConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class DeadLetterReceiver {

    @RabbitListener(queues = QueueConfig.HANDLE_QUEUE)
    public void receive(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        System.out.println("[死信队列]测试 => " + msg);
        int flag = Integer.parseInt(msg.split("-")[1]);
        try {
            Thread.sleep(1000);
            int x = 5 / flag;
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            throw new RuntimeException(e.getMessage());
        }
    }

    @RabbitListener(queues = QueueConfig.FAIL_QUEUE)
    public void receiveFail(String msg) throws IOException {
        System.out.println("[失败队列消费监听] => " + msg + " : " + LocalDateTime.now());
    }

}
