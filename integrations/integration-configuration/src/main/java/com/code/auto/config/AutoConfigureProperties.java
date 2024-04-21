package com.code.auto.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auto")
@Data
public class AutoConfigureProperties {

    private String name;

    private String message;
}