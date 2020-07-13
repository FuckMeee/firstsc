package com.zwh.firstsc.zuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
public class IndexController {
    @GetMapping("test1")
    public String test1() {
        return "test1";
    }
}
