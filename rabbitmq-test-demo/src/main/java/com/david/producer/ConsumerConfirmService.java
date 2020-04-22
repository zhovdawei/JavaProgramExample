package com.david.producer;

import com.david.config.BindingConfig;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerConfirmService {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange topicExchange;

    public boolean sendToAutoConfirmQueue(String msg){
        this.template.convertAndSend(topicExchange.getName(), BindingConfig.ROUTINGKEY_AUTO_CONFIRM_NAME,msg);
        return true;
    }

    public boolean sendToHandConfirmQueue(String msg){
        this.template.convertAndSend(topicExchange.getName(), BindingConfig.ROUTINGKEY_HAND_CONFIRM_NAME,msg);
        return true;
    }

    public boolean sendToHandAckQueue(String msg){
        this.template.convertAndSend(topicExchange.getName(), BindingConfig.ROUTINGKEY_HAND_ACK_NAME,msg);
        return true;
    }

}
