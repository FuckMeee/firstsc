package com.zwh.firstsc.nacosconsumer.controller;

import com.zwh.firstsc.nacosconsumer.feign.NacosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ZhuWeihao
 * @date 2020/7/29
 */
@RestController
@RequestMapping("/nacos")
public class IndexController {
    @Autowired
    private NacosService nacosService;
    @GetMapping("consumer")
    public String nacos01() {
        return nacosService.hello();
    }
}
