package com.elias.chat;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Configuration
public class SecurityConfig {
    @Bean
    // Basic auth with JWT is required to connect to the websocket. The JWT token is
    // provided
    // with the "/login" http endpoint. This token has to be provided as a query
    // param called "token".
    public FilterRegistrationBean<CustomFilter> securityFilter(
            CustomFilter customAuthenticationFilter) {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(customAuthenticationFilter);
        registrationBean.addUrlPatterns("/sockJs", "/ws");
        return registrationBean;
    }

    @Component
    // Custom filter for validating JWT token on protected requests
    private class CustomFilter implements Filter {

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
}
