package com.hb.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAnnotationAspect {

    @Pointcut("@annotation(com.hb.config.MyMonitor)")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(){
        System.out.println();
    }
}
