package com.code.myweb.interceptor;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.common.enums.ErrorCodeEnum;
import com.code.common.pojo.CommonResult;
import com.code.myweb.aop.LimitRepeat;
import com.code.myweb.redis.CacheConstants;
import com.code.myweb.redis.RedisUtil;
import com.code.web.constant.AuthConstants;
import com.code.myweb.util.ServletUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.alibaba.fastjson2.JSON;

@Component
public class LimitRepeatInterceptor implements HandlerInterceptor {
    private static final Cache<String, Object> CACHES =
            CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(5, TimeUnit.SECONDS).build();

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LimitRepeat annotation = method.getAnnotation(LimitRepeat.class);
            if (annotation == null) {
                return true;
            }
            if (checkRepeat(request, annotation)) {
                CommonResult commonResult = CommonResult.error(ErrorCodeEnum.REPEATED_REQUEST);
                ServletUtils.respString(response, JSON.toJSONString(commonResult));
                return false;
            }
        }
        return true;
    }

    private boolean checkRepeat(HttpServletRequest request, LimitRepeat annotation) {
        // 用户 KeyGenerator keyGenerator = new SimpleKeyGenerator();
        String authKey = request.getHeader(AuthConstants.AUTHORIZATION);
        String repeatKey = CacheConstants.REPEAT_KEY + request.getRequestURI() + authKey;
        String key = redisUtil.get(repeatKey);
        if (StringUtils.isEmpty(key)) {
            redisUtil.set(repeatKey, System.currentTimeMillis(), annotation.interval(), TimeUnit.MILLISECONDS);
            return true;
        }
        return false;
    }
}
