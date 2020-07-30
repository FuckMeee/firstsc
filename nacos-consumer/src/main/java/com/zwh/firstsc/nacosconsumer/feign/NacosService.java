package com.zwh.firstsc.nacosconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ZhuWeihao
 * @date 2020/7/29
 */
@FeignClient(name = "nacos-provider", fallback = NacosServiceHystrix.class) // name 服务名 fallback 断路器类
public interface NacosService {
    // 此处名字需要和01服务的controller路径保持一致
    @GetMapping("/nacos/provider")
    String hello();
}
