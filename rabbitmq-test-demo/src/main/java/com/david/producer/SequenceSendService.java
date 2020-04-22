package com.david.producer;

import com.david.config.BindingConfig;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SequenceSendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    public boolean sendSequenceQueue(String msg){
        rabbitTemplate.convertAndSend(topicExchange.getName(), BindingConfig.ROUTINGKEY_SEQUENCE_NAME,msg);
        System.out.println(msg+" : 发送时间 => "+ LocalDateTime.now());
        return true;
    }

    public void sendSequenceMsg(String msg){
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                rabbitTemplate.convertAndSend(topicExchange.getName(), BindingConfig.ROUTINGKEY_SEQUENCE_NAME, msg);
            }
        });
    }


}
