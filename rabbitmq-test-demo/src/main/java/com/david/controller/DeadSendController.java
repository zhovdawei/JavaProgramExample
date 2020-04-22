package com.david.controller;

import com.david.producer.DeadSendService;
import com.david.producer.LengthSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeadSendController {

    @Autowired
    private DeadSendService sendService;

    @Autowired
    private LengthSendService lengthSendService;

    @RequestMapping(value = "/send/dead/msg", method = RequestMethod.GET)
    public String send1(int flag){
        boolean send = sendService.sendDeadLetter("新消息" + ((int) (Math.random() * 1000) + 1)+"-"+flag);
        return send ? "测试死信消息发送成功" : "测试死信消息发送失败";
    }

    @RequestMapping(value = "/send/length/msg", method = RequestMethod.GET)
    public String send2(int flag){
        boolean send = lengthSendService.sendLengthQueue("新消息" + ((int) (Math.random() * 1000) + 1)+"-"+flag);
        return send ? "测试容量限制队列消息发送成功" : "测试容量限制队列消息发送失败";
    }

}
