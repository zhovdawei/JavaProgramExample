package com.david.producer;

import com.david.config.BindingConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class TransactionSendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    /**
     * 以事务模型提交消息
     * 1.获取链接工厂
     * 2.开启连接
     * 3.建立信道(true)
     * 4.开启事务（本方法中已经在建立信道时开启了事务）
     */
    public boolean sendWithTransaction(String msg,int flag) throws IOException, TimeoutException {
        //获取连接工厂
        ConnectionFactory connectionFactory = rabbitTemplate.getConnectionFactory();

        //开启连接 - tcp连接
        Connection connection = connectionFactory.createConnection();

        // 建立信道 构造参数 true代表该信道开启 Transactional 事务模式
        // true - createChannel()方法内部已经调用了channel.txSelect()
        Channel channel = connection.createChannel(true);

        try {
            channel.basicPublish(topicExchange.getName(), BindingConfig.ROUTINGKEY_Transaction_NAME, true,
                    MessageProperties.PERSISTENT_BASIC, msg.getBytes());
            int x = 5 / flag;
            channel.txCommit();
            return true;
        } catch (Exception e) {
            channel.txRollback();
            e.printStackTrace();
        } finally {
            channel.close();
            connection.close();
        }
        return false;
    }

}
