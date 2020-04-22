package com.david.consumer;

import com.david.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class OrderDelayReceiver {

    @RabbitListener(queues = QueueConfig.ORDER_HANDLE_QUEUE)
    public void receiveHandle(String msg) throws IOException {
        String orderId = msg.split(":")[1];
        System.out.println("[监听未支付订单] => " + orderId + " : " + LocalDateTime.now());
    }

}
