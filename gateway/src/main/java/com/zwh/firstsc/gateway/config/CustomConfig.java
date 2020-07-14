package com.zwh.firstsc.gateway.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

//@Configuration
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
}
