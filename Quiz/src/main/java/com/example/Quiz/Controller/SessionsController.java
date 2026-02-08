package com.example.Quiz.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.Service.JwtTokenService;
import com.example.Quiz.Service.UserService;
import com.example.Quiz.models.SessionDetail;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class SessionsController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenService jwtTokenService;

    @GetMapping("/login")
    public String getMethodName() {
        return "Get Login!!";
    }

    @PostMapping("/login")
    public String login(@RequestBody SessionDetail sessionDetail) {
        String username = sessionDetail.getUsername();
        String password = sessionDetail.getPassword();
        if (userService.authenticated(username, password)) {
            // return userService.loadByUsername(username, password);
            String token = jwtTokenService.generateJwtToken(username, password);
            return token;
        } else {
            return null;
        }
    }

}
