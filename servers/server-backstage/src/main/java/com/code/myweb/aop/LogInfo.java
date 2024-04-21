package com.code.myweb.aop;

import com.code.common.enums.OperateType;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface LogInfo {

    /**
     * title
     */
    public String title() default "";

    /**
     * 操作类型
     */
    public OperateType OperateType() default OperateType.DEFAULT;

    /**
     * 保存请求的参数
     */
    public boolean requestData() default true;

    /**
     * 保存响应的参数
     */
    public boolean responseData() default true;

}
