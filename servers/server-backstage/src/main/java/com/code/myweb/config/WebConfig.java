package com.code.myweb.config;

//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

//import com.code.myweb.interceptor.LimitRepeatInterceptor;
import com.code.myweb.interceptor.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private TestInterceptor interceptor;

//    @Resource
//    private LimitRepeatInterceptor interceptor2;


//    /**
//     * FastJsonHttpMessageConverter json-java-json
//     * 去掉null
//     * @return
//     */
//    @Bean
//    public HttpMessageConverters customConverters() {
//        return new HttpMessageConverters(new FastJsonHttpMessageConverter());
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/myweb/**").excludePathPatterns("/static/**");
//        registry.addInterceptor(interceptor2).addPathPatterns("/**");
    }

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置访问源地址
        config.addAllowedOriginPattern("*");
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 有效期 1800秒
        config.setMaxAge(1800L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 映射路径
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
