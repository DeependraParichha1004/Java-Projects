package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/health_check")
public class HealthCheckController {

    @GetMapping
    public String healthcheck(){
        return "Health Check done! Ready to proceed.";
    }
}


