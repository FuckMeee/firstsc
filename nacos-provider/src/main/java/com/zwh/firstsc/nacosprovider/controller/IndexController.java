package com.zwh.firstsc.nacosprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhuWeihao
 * @date 2020/7/29
 */

@RestController
// @RequestMapping("/nacos")
public class IndexController {
    @GetMapping("/nacos/provider")
    public String nacos01() {
        return "nacos provider";
    }

    @GetMapping("/hello/login")
    public String nacos02() throws InterruptedException {
        // Thread.sleep(5000);
        return "/hello/login";
    }
}
