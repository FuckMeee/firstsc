package com.zwh.firstsc.nacosconsumer.feign;

import org.springframework.stereotype.Component;

/**
 * @author ZhuWeihao
 * @date 2020/7/29
 */
@Component
public class NacosServiceHystrix implements NacosService{
    @Override
    public String hello() {
        return "fail";
    }
}
