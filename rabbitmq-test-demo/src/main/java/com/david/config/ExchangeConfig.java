package com.david.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {


    public final static String TOPIC_EXCHANGE = "topicExchange";

    public final static String DEAD_LETTER_EXCHANGE = "deadLetterExchange";

    @Bean
    public TopicExchange topicExchange() {
        /**
         * TopicExchange构造器参数
         * @name 交换机的命名
         * @durable true-交换机重启后还存在；false-重启后不存在
         * @autoDelete true-服务器不再使用这个交换机时删除它；false-不需要自动删除
         */
        return new TopicExchange(TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public TopicExchange deadLetterExchange() {
        return new TopicExchange(DEAD_LETTER_EXCHANGE);
    }

}
