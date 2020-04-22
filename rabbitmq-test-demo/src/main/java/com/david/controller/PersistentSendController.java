package com.david.controller;

import com.david.config.BindingConfig;
import com.david.producer.PersistentSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersistentSendController {

    @Autowired
    private PersistentSendService sendService;

    @RequestMapping(value = "/send/persistent/msg", method = RequestMethod.GET)
    public String send() {
        boolean flag = sendService.sendToPersistentQueue(BindingConfig.ROUTINGKEY_PERSISTENT_NAME,
                "新消息" + ((int) (Math.random() * 1000) + 1));
        return flag ? "发送成功" : "发送失败";
    }

}
