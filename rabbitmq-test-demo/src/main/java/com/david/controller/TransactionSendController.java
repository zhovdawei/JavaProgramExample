package com.david.controller;

import com.david.producer.TransactionSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class TransactionSendController {

    @Autowired
    private TransactionSendService sendService;

    @RequestMapping(value = "/send/transaction/msg", method = RequestMethod.GET)
    public String sendWithTransaction(int flag) throws IOException, TimeoutException {
        boolean send = sendService.sendWithTransaction("新消息" + ((int) (Math.random() * 1000) + 1), flag);
        return send ? "事务消息发送成功" : "事务消息发送失败";
    }

}
