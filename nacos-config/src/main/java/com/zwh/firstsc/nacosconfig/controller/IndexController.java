package com.zwh.firstsc.nacosconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhuWeihao
 * @date 2020/7/29
 */
@RestController
@RequestMapping("nacos")
@RefreshScope // 动态刷新配置
public class IndexController {
    @Value("${nacos.config}")
    private String config;

    @GetMapping("config")
    public String config() {
        return config;
    }
}
