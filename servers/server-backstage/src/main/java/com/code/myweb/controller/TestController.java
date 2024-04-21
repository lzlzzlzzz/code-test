package com.code.myweb.controller;

import com.code.common.pojo.CommonResult;
import com.code.myweb.domain.MyAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.code.common.pojo.CommonResult.success;

/**
 * 用户信息Controller
 */
@RestController
@RequestMapping("/myweb/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * 获取用户信息详细信息
     */
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable("id") Long id) {
        logger.info("======================id:{}", id);
        return success();
    }

    @GetMapping(value = "/config")
    public CommonResult buyPhone(@Validated MyAccount account, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, Object> errorMap = new HashMap<>(10);
            fieldErrors.forEach(error -> {
                        logger.error("字段:{}校验失败, Message:{} \n", error.getField(), error.getDefaultMessage());
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
            );
        }
        return success();
    }

}
