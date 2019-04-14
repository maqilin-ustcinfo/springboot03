package com.dn.springboot03.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url  = ((HttpServletRequest)request).getRequestURL().toString();
        System.out.println("MyFilter===="+url);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
