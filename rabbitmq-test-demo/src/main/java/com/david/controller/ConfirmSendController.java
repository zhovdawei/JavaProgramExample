package com.david.controller;

import com.david.producer.ConfirmSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class ConfirmSendController {

    @Autowired
    private ConfirmSendService sendService;

    @RequestMapping(value = "/send/sync/msg", method = RequestMethod.GET)
    public String sendWithSyncConfirm() throws IOException, TimeoutException {
        boolean send = sendService.sendWithSyncConfirm("新消息" + ((int) (Math.random() * 1000) + 1));
        return send ? "同步确认消息发送成功" : "同步确认消息发送失败";
    }

    @RequestMapping(value = "/send/async/msg", method = RequestMethod.GET)
    public String sendWithAsyncConfirm() {
        sendService.sendWithAsyncConfirm("新消息" + ((int) (Math.random() * 1000) + 1));
        return "异步确认消息发送动作完成";
    }
}
