package com.david.producer;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersistentSendService {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange topicExchange;

    public boolean sendToPersistentQueue(String routingKey,String msg){
        this.template.convertAndSend(topicExchange.getName(),routingKey,msg);
        return true;
    }

}
