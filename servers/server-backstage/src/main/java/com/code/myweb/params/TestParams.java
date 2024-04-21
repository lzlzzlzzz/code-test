package com.code.myweb.params;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = "classpath:config/test.properties")
@ConfigurationProperties(prefix = "project")
@Component
@Data
public class TestParams {

    private String name;

    private String message;
}
