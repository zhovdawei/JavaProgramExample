package com.david.controller;

import com.david.producer.ConsumerConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerSendController {

    @Autowired
    private ConsumerConfirmService sendService;

    @RequestMapping(value = "/send/auto/msg", method = RequestMethod.GET)
    public String sendToAutoConfirmQueue(int flag){
        boolean send = sendService.sendToAutoConfirmQueue("自动确认新消息" + ((int) (Math.random() * 1000) + 1) + "-" + flag);
        return send?"自动确认测试消息发送->成功":"消费确认测试消息发送->失败";
    }

    @RequestMapping(value = "/send/hand/msg", method = RequestMethod.GET)
    public String sendToHandConfirmQueue(int flag){
        boolean send = sendService.sendToHandConfirmQueue("手动确认新消息" + ((int) (Math.random() * 1000) + 1) + "-" + flag);
        return send?"手动确认测试消息发送->成功":"消费确认测试消息发送->失败";
    }

    @RequestMapping(value = "/send/ack/msg", method = RequestMethod.GET)
    public String sendToHandAckQueue(int flag){
        boolean send = sendService.sendToHandAckQueue("[HandAck消息]" + ((int) (Math.random() * 1000) + 1) + "-" + flag);
        return send?"HandAck测试消息发送->成功":"HandAck测试消息发送->失败";
    }

}
