package com.david.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindingConfig {

    public final static String ROUTINGKEY_PERSISTENT_NAME = "persistentKey";

    public final static String ROUTINGKEY_Transaction_NAME = "transactionKey";

    public final static String ROUTINGKEY_CONFIRM_NAME = "confirmKey";

    public final static String ROUTINGKEY_AUTO_CONFIRM_NAME = "autoConfirmKey";

    public final static String ROUTINGKEY_HAND_CONFIRM_NAME = "handConfirmKey";

    public final static String ROUTINGKEY_DELAY_NAME = "delayKey";

    public final static String ROUTINGKEY_FAIL_NAME = "failKey";

    public final static String ROUTINGKEY_HANDLE_NAME = "handleKey";

    public final static String ROUTINGKEY_LENGTH_NAME = "lengthKey";

    public final static String ROUTINGKEY_ORDER_HANDLE_NAME = "orderHandleKey";

    public final static String ROUTINGKEY_ORDER_DELAY_NAME = "orderDelayKey";

    public final static String ROUTINGKEY_HAND_ACK_NAME = "handAckKey";

    public final static String ROUTINGKEY_HAND_RETRY_NAME = "handRetryKey";

    public final static String ROUTINGKEY_HAND_FAIL_NAME = "handFailKey";

    public final static String ROUTINGKEY_SEQUENCE_NAME = "sequenceQueue";

    @Bean
    public Binding persistentBinding(TopicExchange topicExchange, Queue persistentQueue) {
        return BindingBuilder.bind(persistentQueue).to(topicExchange).with(ROUTINGKEY_PERSISTENT_NAME);
    }

    @Bean
    public Binding transactionBinding(TopicExchange topicExchange, Queue transactionQueue) {
        return BindingBuilder.bind(transactionQueue).to(topicExchange).with(ROUTINGKEY_Transaction_NAME);
    }

    @Bean
    public Binding confirmBinding(TopicExchange topicExchange, Queue confirmQueue) {
        return BindingBuilder.bind(confirmQueue).to(topicExchange).with(ROUTINGKEY_CONFIRM_NAME);
    }

    @Bean
    public Binding autoConfirmBinding(TopicExchange topicExchange, Queue autoConfirmQueue) {
        return BindingBuilder.bind(autoConfirmQueue).to(topicExchange).with(ROUTINGKEY_AUTO_CONFIRM_NAME);
    }

    @Bean
    public Binding handConfirmBinding(TopicExchange topicExchange, Queue handConfirmQueue) {
        return BindingBuilder.bind(handConfirmQueue).to(topicExchange).with(ROUTINGKEY_HAND_CONFIRM_NAME);
    }

    @Bean
    public Binding delayBinding(TopicExchange topicExchange, Queue delayQueue) {
        return BindingBuilder.bind(delayQueue).to(topicExchange).with(ROUTINGKEY_DELAY_NAME);
    }

    @Bean
    public Binding failBinding(TopicExchange deadLetterExchange, Queue failQueue) {
        return BindingBuilder.bind(failQueue).to(deadLetterExchange).with(ROUTINGKEY_FAIL_NAME);
    }

    @Bean
    public Binding handleBinding(TopicExchange topicExchange, Queue handleQueue) {
        return BindingBuilder.bind(handleQueue).to(topicExchange).with(ROUTINGKEY_HANDLE_NAME);
    }

    @Bean
    public Binding lengthBinding(TopicExchange topicExchange, Queue lengthQueue) {
        return BindingBuilder.bind(lengthQueue).to(topicExchange).with(ROUTINGKEY_LENGTH_NAME);
    }

    @Bean
    public Binding orderHandleBinding(TopicExchange deadLetterExchange, Queue orderHandleQueue) {
        return BindingBuilder.bind(orderHandleQueue).to(deadLetterExchange).with(ROUTINGKEY_ORDER_HANDLE_NAME);
    }

    @Bean
    public Binding orderDelayBinding(TopicExchange topicExchange, Queue orderDelayQueue) {
        return BindingBuilder.bind(orderDelayQueue).to(topicExchange).with(ROUTINGKEY_ORDER_DELAY_NAME);
    }

    @Bean
    public Binding handAckBinding(TopicExchange topicExchange, Queue handAckQueue) {
        return BindingBuilder.bind(handAckQueue).to(topicExchange).with(ROUTINGKEY_HAND_ACK_NAME);
    }

    @Bean
    public Binding handRetryBinding(TopicExchange deadLetterExchange, Queue handRetryQueue) {
        return BindingBuilder.bind(handRetryQueue).to(deadLetterExchange).with(ROUTINGKEY_HAND_RETRY_NAME);
    }

    @Bean
    public Binding handFailBinding(TopicExchange topicExchange, Queue handFailQueue) {
        return BindingBuilder.bind(handFailQueue).to(topicExchange).with(ROUTINGKEY_HAND_FAIL_NAME);
    }

    @Bean
    public Binding sequenceBinding(TopicExchange topicExchange, Queue sequenceQueue) {
        return BindingBuilder.bind(sequenceQueue).to(topicExchange).with(ROUTINGKEY_SEQUENCE_NAME);
    }

}
