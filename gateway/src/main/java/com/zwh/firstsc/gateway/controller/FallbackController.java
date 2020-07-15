package com.zwh.firstsc.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallbackController {
    @RequestMapping("/defaultFallback")
    public Map<String, String> defaultFallback() {
        System.out.println("====================服务熔断");
        Map<String, String> map = new HashMap<>();
        map.put("code", "500");
        map.put("msg", "服务熔断");
        return map;
    }
}
