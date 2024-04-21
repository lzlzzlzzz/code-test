package com.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 */
@MapperScan("com.code.**.mapper")
@SpringBootApplication()
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BgApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        ConfigurableApplicationContext context = SpringApplication.run(BgApplication.class, args);
        System.out.println("启动成功=============================");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BgApplication.class);
    }

}
