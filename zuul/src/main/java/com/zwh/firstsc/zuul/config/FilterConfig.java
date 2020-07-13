package com.zwh.firstsc.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.zwh.firstsc.zuul.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public ZuulFilter tokenFilter() {
        return new TokenFilter();
    }
}
