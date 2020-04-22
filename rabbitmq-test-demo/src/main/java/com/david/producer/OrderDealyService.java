package com.david.producer;

import com.david.config.BindingConfig;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderDealyService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    public boolean sendOrderDelayQueue(String msg){
        rabbitTemplate.convertAndSend(topicExchange.getName(), BindingConfig.ROUTINGKEY_ORDER_DELAY_NAME,msg);
        System.out.println(msg+" : 发送时间 => "+ LocalDateTime.now());
        return true;
    }

}
