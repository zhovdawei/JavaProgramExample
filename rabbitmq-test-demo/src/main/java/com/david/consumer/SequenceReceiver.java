package com.david.consumer;

import com.alibaba.fastjson.JSON;
import com.david.config.QueueConfig;
import com.david.entity.MsgEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SequenceReceiver {

    @RabbitListener(queues = QueueConfig.SEQUENCE_QUEUE)
    public void receiveOne(String msg) {
        System.out.println("[顺序消费者1] => " + msg);

        MsgEntity entity = JSON.parseObject(msg,MsgEntity.class);



        String orderId = msg.split(":")[1];
        System.out.println("[监听未支付订单] => " + orderId + " : " + LocalDateTime.now());
    }


}
