package com.david.consumer;

import com.david.config.BindingConfig;
import com.david.config.ExchangeConfig;
import com.david.config.QueueConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class ConsumerConfirmReceiver {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = QueueConfig.AUTO_CONFIRM_QUEUE)
    public void receviceMsgAutoConfirm(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        System.out.println("消费确认测试 => " + msg);
        int flag = Integer.parseInt(msg.split("-")[1]);
        try {
            Thread.sleep(5000);
            int x = 5 / flag;
        } catch (Exception e) {
            /**
             * @param deliveryTag 来自收到的标签
             * @param multiple    拒绝所有消息，包括提供的传递标签，为true；否，仅拒绝提供的交付标签
             * @param requeue     如果应该重新排队被拒绝的消息，而不是而不是被丢弃/按照字母顺序排列，则为true
             * */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            throw new RuntimeException(e.getMessage());
        }
    }

    @RabbitListener(queues = QueueConfig.HAND_CONFIRM_QUEUE)
    public void receviceMsgHandConfirm(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        System.out.println("消费确认测试 => " + msg);
        int flag = Integer.parseInt(msg.split("-")[1]);
        // 判断消费逻辑是否出现异常
        try {
            Thread.sleep(5000);
            int x = 5 / flag;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // 消费逻辑出现异常-消息重新放入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
        //消费逻辑正常
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = QueueConfig.HAND_ACK_QUEUE)
    public void receive(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        long retryCount = getRetryCount(message.getMessageProperties());
        if (retryCount > 3) {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            rabbitTemplate.convertAndSend(ExchangeConfig.TOPIC_EXCHANGE, BindingConfig.ROUTINGKEY_HAND_FAIL_NAME, msg);
            return;
        }
        int flag = Integer.parseInt(msg.split("-")[1]);
        System.out.println("[HandAck消费监听器]第 " + retryCount + " 次消费，消息为 => " + msg + ",时间为 =>" + LocalDateTime.now());

        try {
            int x = 5 / flag;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // 消费逻辑出现异常-消息重新放入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
        //消费逻辑正常
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 获取消息被重试的次数
     */
    private long getRetryCount(MessageProperties messageProperties) {
        Long retryCount = 0L;
        if (null != messageProperties) {
            List<Map<String, ?>> deaths = messageProperties.getXDeathHeader();
            if (deaths != null && deaths.size() > 0) {
                Map<String, Object> death = (Map<String, Object>) deaths.get(0);
                retryCount = (Long) death.get("count");
            }
        }
        return retryCount;
    }

}
