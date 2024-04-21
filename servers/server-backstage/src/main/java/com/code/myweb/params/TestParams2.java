package com.code.myweb.params;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = "classpath:config/test.properties")
@Component
@Data
public class TestParams2 {

    @Value("${project.name}")
    private String name;

    @Value("${project.message}")
    private String message;
}
