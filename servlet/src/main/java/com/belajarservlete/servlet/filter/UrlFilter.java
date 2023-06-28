package com.belajarservlete.servlet.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns = {"/*"}) @Slf4j
public class UrlFilter extends HttpFilter {
    
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Request recive from URL ".concat(request.getRequestURI()));
        // chain ini digunakan jikalau kita ingin melanjutkan request
        // jikalau tidak inigin melanjutkan request maka tidak perlu di chain.dofilter
        chain.doFilter(request, response);
    }
}
