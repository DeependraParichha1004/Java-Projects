package com.example.Quiz;

import com.example.Quiz.Service.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 1. Get headers using the request object (Standard approach)
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String staffType = request.getHeader("staff_type");

        String auth_token = request.getHeader("AUTHORIZATION");
        String staff_type = request.getHeader("staff_type");
        System.out.println("staff type is: " + staff_type);
        try {
            System.out.println("token is: " + auth_token);
            if (jwtTokenService.validAuthToken(auth_token)) {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}