package com.elias.chat;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class SecurityConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("Entering filter");
        if (servletRequest instanceof HttpServletRequest) {

            /*
             * TODO: use authorization header
             * HttpServletRequest request = (HttpServletRequest) servletRequest;
             * String authorization = request.getHeader("Authorization");
             */

            // String x = "1";
            // if ("1".equals(x)) {
            System.out.println("CALLING DOFILTER\n");
            filterChain.doFilter(servletRequest, servletResponse);
            // } else {
            // System.out.println("Bad request login\n");
            // HttpServletResponse response = (HttpServletResponse) servletResponse;
            // response.setStatus(HttpStatus.UNAUTHORIZED.value());
            // response.setContentType("application/json;charset=UTF-8");
            // response.getWriter().write("{\"message\": \"Bad login\"}");
            // }
        }
    }
}
