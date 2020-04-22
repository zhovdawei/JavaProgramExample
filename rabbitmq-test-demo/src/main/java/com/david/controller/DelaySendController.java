package com.david.controller;

import com.david.producer.DelaySendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DelaySendController {

    @Autowired
    private DelaySendService sendService;

    @RequestMapping(value = "/send/delay/msg", method = RequestMethod.GET)
    public String send(){
        boolean flag = sendService.sendDelayQueue("新消息" + ((int) (Math.random() * 1000) + 1));
        return flag ? "延迟消息发送成功" : "延迟消息发送失败";
    }

}
