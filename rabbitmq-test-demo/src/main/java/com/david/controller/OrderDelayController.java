package com.david.controller;

import com.david.producer.DelaySendService;
import com.david.producer.OrderDealyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDelayController {

    @Autowired
    private OrderDealyService sendService;

    @RequestMapping(value = "/send/order/msg", method = RequestMethod.GET)
    public String send(){
        boolean flag = sendService.sendOrderDelayQueue("[未支付订单消息]订单号:" + ((int) (Math.random() * 1000000) + 1));
        return flag ? "未支付订单消息发送成功" : "未支付订单消息发送失败";
    }


}
