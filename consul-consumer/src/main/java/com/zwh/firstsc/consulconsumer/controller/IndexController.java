package com.zwh.firstsc.consulconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhuWeihao
 * @date 2020/7/22
 */

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @GetMapping("/call")
    public String call() {
        ServiceInstance serviceInstance = loadBalancer.choose("consul-provider");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/index/hello", String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }
}
