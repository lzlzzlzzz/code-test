package com.code.myweb.config;

import com.code.myweb.filter.MyFilter1;
import com.code.myweb.filter.MyFilter2;
import com.code.myweb.filter.RepeatableFilter;
import com.code.myweb.filter.XssFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FilterConfig {

    @Value("${xss.excludes}")
    private String excludes;

    @Value("${xss.urlPatterns}")
    private String urlPatterns;

    /**
     * ResponseBodyAdvice
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean<MyFilter1> filterContainer = new FilterRegistrationBean<>();
        filterContainer.setFilter(new MyFilter1());
        filterContainer.addUrlPatterns("/myweb/*");
        filterContainer.setName("MyFilter1");
        filterContainer.setOrder(1);
        return filterContainer;
    }

    @Bean
    public FilterRegistrationBean myFilter2() {
        FilterRegistrationBean<MyFilter2> filterContainer = new FilterRegistrationBean<>();
        filterContainer.setFilter(new MyFilter2());
        filterContainer.addUrlPatterns("/myweb/*");
        filterContainer.setName("MyFilter2");
        filterContainer.setOrder(2);
        return filterContainer;
    }

    @Bean
    @ConditionalOnProperty(value = "xss.enabled", havingValue = "true")
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        // 过滤
        registration.addUrlPatterns(StringUtils.split(urlPatterns, ","));
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE - 1);
        Map initParameters = new HashMap<String, String>();
        // 排除
        initParameters.put("excludes", excludes);
        registration.setInitParameters(initParameters);
        return registration;
    }

    @Bean
    public FilterRegistrationBean repeatableFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RepeatableFilter());
        registration.addUrlPatterns("/*");
        registration.setName("repeatableFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

}
