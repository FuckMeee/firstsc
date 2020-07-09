package com.zwh.firstsc.consumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonRule {
    @Bean
    public IRule myRule() {
        return new MyRibbonRule();
    }
}
