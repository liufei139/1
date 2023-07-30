package com.example.config;

import org.dom4j.io.SAXReader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 第三方bean对象的注入测试类
 */
@Configuration
public class CommonConfig {
    @Bean
    @ConditionalOnClass(name="com.example.SAXRead")
    public SAXReader saxReader(){
        return new SAXReader();
    }
}
