package com.david.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.david.client"})
public class HelloServiceAutoConfiguration {
}
