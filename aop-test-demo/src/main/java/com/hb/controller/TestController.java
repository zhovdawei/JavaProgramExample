package com.hb.controller;

import com.david.client.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @RequestMapping(value = "/test/get/print",method = RequestMethod.GET)
    public String testM(int id){
        return "ID is "+id;
    }

    @RequestMapping(value = "/test/get/print",method = RequestMethod.POST)
    public String testM1(@PathVariable("name")String name){
        return "your name is "+name;
    }

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/test/say/hello",method = RequestMethod.GET)
    public String sayHello(){
        return helloService.sayHello();
    }

}
