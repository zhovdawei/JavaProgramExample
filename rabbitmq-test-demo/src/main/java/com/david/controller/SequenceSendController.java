package com.david.controller;

import com.david.producer.SequenceSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SequenceSendController {

    @Autowired
    private SequenceSendService sendService;

    @RequestMapping(value = "/send/sequence/msg", method = RequestMethod.GET)
    public String send(int flag){



        return "OK";
    }

}
