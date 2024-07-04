package com.mirror.summer.aop.after;

import com.mirror.summer.annotation.Around;
import com.itranswarp.summer.annotation.Component;

@Component
@Around("politeInvocationHandler")
public class GreetingBean {

    public String hello(String name) {
        return "Hello, " + name + ".";
    }

    public String morning(String name) {
        return "Morning, " + name + ".";
    }
}
