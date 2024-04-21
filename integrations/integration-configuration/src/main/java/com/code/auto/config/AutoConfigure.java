package com.code.auto.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(AutoConfigureService.class)
@EnableConfigurationProperties(AutoConfigureProperties.class)
public class AutoConfigure {

    public String getName() {
        return "AutoConfigure getName";
    }

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    public AutoConfigureService autoConfigureServiceInit() {
        System.out.println("AutoConfigure autoConfigureServiceInit");
        return new AutoConfigureService();
    }
}