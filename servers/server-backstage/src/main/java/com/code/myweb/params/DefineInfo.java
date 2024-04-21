package com.code.myweb.params;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "define.userinfo1")
@Component
@Data
public class DefineInfo {
    private String username;
    private int age;
}
