package com.zwh.firstsc.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/index")
    public String index(@RequestParam("str") String str) {
        return "hello " + str;
    }

    @GetMapping("/index2")
    public String index2() {
        logger.info("request name is index2");
        try{
            Thread.sleep(1000000);
        }catch ( Exception e){
            logger.error(" hello two error",e);
        }
        return "hello zwh2";
    }

    @GetMapping("/index3")
    public String index3() {
        return "hello zwh3";
    }
}
