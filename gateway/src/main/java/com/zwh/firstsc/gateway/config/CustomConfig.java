package com.zwh.firstsc.gateway.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import reactor.core.publisher.Mono;

@Configuration
public class CustomConfig {
//    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {

        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new FileSystemResource("jwt.yml")); // File引入
//		yaml.setResources(new ClassPathResource("youryml.yml")); // class引入
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

    @Bean
    public KeyResolver hostAddrKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    // @Bean
    // public KeyResolver userKeyResolver() {
    //     return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    // }
    //
    // @Bean
    // public KeyResolver apiKeyResolver() {
    //     return exchange -> Mono.just(exchange.getRequest().getPath().value());
    // }
}
