package com.elias.chat;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.elias.chat.models.User;
import com.elias.chat.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Configuration
public class SecurityConfig {
    @Bean
    // Basic auth with JWT is required to connect to the websocket. The JWT token is
    // provided with the "/login" http endpoint. This token has to be provided as a
    // query
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

        @Autowired
        private UserRepository userRepo;

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                throws IOException, ServletException {

            // if (!servletRequest instanceof HttpServletRequest) {
            // throw
            // }

            // TODO: use authorization header
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            final String authHeader = request.getHeader("Authorization");

            filterChain.doFilter(servletRequest, servletResponse);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            // If the token is valid, extract the username, find the related user in the
            // database,
            // and set it in the authentication context so you can access it in any
            // application layer.
            try {
                final String jwt = authHeader.substring(7);
                final String userEmail = jwtService.extractUsername(jwt);

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (userEmail != null && authentication == null) {
                    Optional<User> userDetails = this.userRepo.findByEmail(userEmail);

                    if (userDetails.isEmpty()) {
                        // do something
                    }

                    if (jwtService.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }

                filterChain.doFilter(request, response);
            } catch (Exception exception) {
                handlerExceptionResolver.resolveException(request, response, null, exception);
            }
        }
    }
}
