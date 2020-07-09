package com.zwh.firstsc.consumer.controller;

import com.zwh.firstsc.consumer.feign.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController2 {
    @Autowired
    private HelloService helloService;
    @GetMapping("/index")
    String index(@RequestParam("str") String str) {
        return helloService.hello(str);
    }
}