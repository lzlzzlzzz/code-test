package com.code.myweb.util;

import com.code.web.constant.WebConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 *  Servlet工具
 */
public class ServletUtils {

    private static final Logger logger = LoggerFactory.getLogger(ServletUtils.class);

    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes requestAttributes;
        try {
            requestAttributes = (ServletRequestAttributes)
                    RequestContextHolder.currentRequestAttributes();
            // 线程 共享Request (线程传递)
            RequestContextHolder.setRequestAttributes(requestAttributes, true);
            return requestAttributes.getRequest();
        } catch (IllegalStateException e) {
            return null;
        }
    }

    public static HttpServletResponse getCurrentResponse() {
        HttpServletResponse response;
        try {
            response = ((ServletRequestAttributes)
                    RequestContextHolder.currentRequestAttributes()).getResponse();
        } catch (Exception e) {
            return null;
        }
        return response;
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }


    /**
     * 获得所有请求参数
     *
     * @param request 请求对象{@link ServletRequest}
     * @return Map
     */
    public static Map<String, String> getParamMap(ServletRequest request) {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : getParams(request).entrySet()) {
            params.put(entry.getKey(), StringUtils.join(entry.getValue(), ","));
        }
        return params;
    }

    public static Map<String, String[]> getParams(ServletRequest request) {
        final Map<String, String[]> map = request.getParameterMap();
        return Collections.unmodifiableMap(map);
    }


    /**
     * 字符串渲染
     *
     * @param response response
     * @param string string
     */
    public static void respString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            logger.error("ServletUtils respString", e);
        }
    }


    /**
     * url编码
     *
     * @param str str
     * @return String
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, WebConstants.UTF8);
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * url解码
     *
     * @param str str
     * @return String
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, WebConstants.UTF8);
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }
}
