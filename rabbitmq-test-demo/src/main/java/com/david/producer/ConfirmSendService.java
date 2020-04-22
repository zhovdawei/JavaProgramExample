package com.david.producer;

import com.david.config.BindingConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Component
public class ConfirmSendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    /**
     * 1.获取链接工厂
     * 2.开启连接
     * 3.建立信道(false)
     * 4.开启发布确认模式
     **/
    public boolean sendWithSyncConfirm(String msg) throws IOException, TimeoutException {
        //获取连接工厂
        ConnectionFactory connectionFactory = rabbitTemplate.getConnectionFactory();

        //开启连接 - tcp连接
        Connection connection = connectionFactory.createConnection();

        // 建立信道
        // false - 不开启事务
        Channel channel = connection.createChannel(false);
//        Channel channel = connection.createChannel(true);

        try {
            //开启发布确认模式
            channel.confirmSelect();
            channel.basicPublish(topicExchange.getName(), BindingConfig.ROUTINGKEY_CONFIRM_NAME, true,
                    MessageProperties.PERSISTENT_BASIC, msg.getBytes());


            if (channel.waitForConfirms()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.close();
            connection.close();
        }
        return false;
    }

    /**
     * 异步应答
     **/
    public void sendWithAsyncConfirm(String msg) {
        //获取回调
        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey) -> {
            String correlationId = message.getMessageProperties().getHeaders().get("spring_returned_message_correlation").toString();
            String oriMsg = new String(message.getBody());
            System.out.println("消息ID: " + correlationId + "\n"
                    + "消息: " + message + "\n"
                    + "应答码: " + replyCode + "\n"
                    + "原因: " + replyText + "\n"
                    + "交换机: " + exchange + "\n"
                    + "路由键: " + routingKey + "\n"
                    + "原消息: " + oriMsg + "\n");
        });

        // 获取异步确认
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            //只要消息抵达了交换机，ack就返回true
            if (ack) {
                System.out.println("消息id为: " + correlationData + "的消息，已经被ack成功" + cause);
            } else {
                System.out.println("消息id为: " + correlationData + "的消息，消息nack，失败原因是：" + cause);
            }
        });

        //发送消息
        rabbitTemplate.convertAndSend(topicExchange.getName(), BindingConfig.ROUTINGKEY_CONFIRM_NAME, msg,
                new CorrelationData(UUID.randomUUID().toString().replace("-", "")));
    }

}
