package com.zwh.firstsc.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider", fallback = HelloServiceHystrix.class)
public interface HelloService {
    // 此处名字需要和01服务的controller路径保持一致
    @GetMapping("/hello/index")
    String hello(@RequestParam("str") String str); // 参数一定一定一定要加@RequestParam注解!!!
}
