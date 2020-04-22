package com.david.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfig {

    /**
     * 测试持久化队列
     */
    public final static String PERSISTENT_QUEUE = "PersistentQueue";

    /**
     * 测试提交事务队列
     */
    public final static String TRANSACTION_QUEUE = "TransactionQueue";

    /**
     * 测试消息投递确认机制
     */
    public final static String CONFIRM_QUEUE = "ConfirmQueue";

    /**
     * 测试消息消费自动应答
     */
    public final static String AUTO_CONFIRM_QUEUE = "AutoConfirmQueue";

    /**
     * 测试消息消费自动应答
     */
    public final static String HAND_CONFIRM_QUEUE = "HandConfirmQueue";

    /**
     * 延迟队列
     */
    public final static String DELAY_QUEUE = "DelayQueue";

    /**
     * 失败队列
     */
    public final static String FAIL_QUEUE = "FailQueue";

    /**
     * 逻辑处理队列
     */
    public final static String HANDLE_QUEUE = "HandleQueue";

    /**
     * 长度为3的队列
     */
    public final static String LENGTH_QUEUE = "LengthQueue";


    /**
     * 订单处理队列
     */
    public final static String ORDER_HANDLE_QUEUE = "OrderHandleQueue";

    /**
     * 订单延迟队列
     */
    public final static String ORDER_DELAY_QUEUE = "OrderDelayQueue";

    /**
     * 手动应答队列
     */
    public final static String HAND_ACK_QUEUE = "HandAckQueue";

    /**
     * 手动应答重试队列
     */
    public final static String HAND_RETRY_QUEUE = "HandRetryQueue";

    /**
     * 手动应答错误队列
     */
    public final static String HAND_FAIL_QUEUE = "HandFailQueue";

    /**
     * 顺序消费队列
     */
    public final static String SEQUENCE_QUEUE = "SequenceQueue";



    @Bean
    public Queue persistentQueue() {
        /**
         * Queue构造器参数
         * @name 队列的命名
         * 不显示设置druable的队列，默认是持久化
         */
        return new Queue(PERSISTENT_QUEUE);
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue(TRANSACTION_QUEUE);
    }

    @Bean
    public Queue confirmQueue() {
        return new Queue(CONFIRM_QUEUE);
    }

    @Bean
    public Queue autoConfirmQueue() {
        return new Queue(AUTO_CONFIRM_QUEUE);
    }

    @Bean
    public Queue handConfirmQueue() {
        return new Queue(HAND_CONFIRM_QUEUE);
    }

    @Bean
    public Queue failQueue(){
        return new Queue(FAIL_QUEUE,false);
    }

    @Bean
    public Queue delayQueue(){
        Map<String, Object> args = new HashMap<>();
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", ExchangeConfig.DEAD_LETTER_EXCHANGE);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", BindingConfig.ROUTINGKEY_FAIL_NAME);
        args.put("x-message-ttl",5000);
        return new Queue(DELAY_QUEUE,false,false,false,args);
    }

    @Bean
    public Queue handleQueue(){
        Map<String, Object> args = new HashMap<>();
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", ExchangeConfig.DEAD_LETTER_EXCHANGE);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", BindingConfig.ROUTINGKEY_FAIL_NAME);
        return new Queue(HANDLE_QUEUE,false,false,false,args);
    }

    @Bean
    public Queue lengthQueue(){
        Map<String, Object> args = new HashMap<>();
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", ExchangeConfig.DEAD_LETTER_EXCHANGE);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", BindingConfig.ROUTINGKEY_FAIL_NAME);
        args.put("x-max-length",3);
        return new Queue(LENGTH_QUEUE,false,false,false,args);
    }

    @Bean
    public Queue orderHandleQueue(){
        return new Queue(ORDER_HANDLE_QUEUE);
    }

    @Bean
    public Queue orderDelayQueue(){
        Map<String, Object> args = new HashMap<>();
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", ExchangeConfig.DEAD_LETTER_EXCHANGE);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", BindingConfig.ROUTINGKEY_ORDER_HANDLE_NAME);
        //x-message-ttl  这里声明消息的超时时间
        args.put("x-message-ttl",10000);
        return new Queue(ORDER_DELAY_QUEUE,false,false,false,args);
    }

    @Bean
    public Queue handAckQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", ExchangeConfig.DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", BindingConfig.ROUTINGKEY_HAND_RETRY_NAME);
        return new Queue(HAND_ACK_QUEUE,false,false,false,args);
    }

    @Bean
    public Queue handRetryQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", ExchangeConfig.TOPIC_EXCHANGE);
        args.put("x-dead-letter-routing-key", BindingConfig.ROUTINGKEY_HAND_ACK_NAME);
        args.put("x-message-ttl",5000);
        return new Queue(HAND_RETRY_QUEUE,false,false,false,args);
    }

    @Bean
    public Queue handFailQueue(){
        return new Queue(HAND_FAIL_QUEUE);
    }

    @Bean
    public Queue sequenceQueue(){
        return new Queue(SEQUENCE_QUEUE);
    }
}
