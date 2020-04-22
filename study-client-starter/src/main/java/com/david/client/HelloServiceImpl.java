package com.david.client;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {
    public String sayHello() {
        return "Hello!";
    }
}
