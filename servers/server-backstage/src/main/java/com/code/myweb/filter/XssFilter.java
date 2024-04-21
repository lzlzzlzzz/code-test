package com.code.myweb.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * XSS过滤器
 */
public class XssFilter implements Filter {

    /**
     * 自定义排除url
     */
    public List excludes = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) {
        String tempExcludes = filterConfig.getInitParameter("excludes");
        if (StringUtils.isNotEmpty(tempExcludes)) {
            String[] url = tempExcludes.split(",");
            for (int i = 0; url != null && i < url.length; i++) {
                excludes.add(url[i]);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getServletPath();
        String method = req.getMethod();
        // 免xss的方法
        if (method == null || HttpMethod.GET.matches(method) || HttpMethod.DELETE.matches(method)) {
            chain.doFilter(request, response);
            return;
        }
        if(urlsMatch(url, excludes)){
            chain.doFilter(request, response);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    /**
     * ？匹配一个字符
     * *匹配0个或多个字符
     * **匹配0个或多个目录
     * url专用匹配
     *
     * @param url
     * @param urls
     * @return
     */
    public static boolean urlsMatch(String url, List<String> urls) {
        if (StringUtils.isEmpty(url) || CollectionUtils.isEmpty(urls)) {
            return false;
        }
        AntPathMatcher matcher = new AntPathMatcher();
        for (String subUrl : urls) {
            return matcher.match(subUrl, url);
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}