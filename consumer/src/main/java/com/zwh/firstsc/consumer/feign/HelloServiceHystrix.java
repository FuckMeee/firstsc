package com.zwh.firstsc.consumer.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloServiceHystrix implements HelloService{
    @Override
    public String hello(@RequestParam("str") String str) {
        return "hello" + str + ", this messge send failed ";
    }
}
