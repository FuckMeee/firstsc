package com.zwh.firstsc.consul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhuWeihao
 * @date 2020/7/22
 */

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("hello")
    public String hello() {
        return "hello consul";
    }
}
