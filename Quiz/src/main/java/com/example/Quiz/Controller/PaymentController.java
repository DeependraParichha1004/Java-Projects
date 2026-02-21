package com.example.Quiz.Controller;

import com.example.Quiz.models.Payment;
import com.razorpay.RazorpayException;
import com.example.Quiz.Service.JwtTokenService;
import com.example.Quiz.Service.PaymentService;
import com.example.Quiz.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    JwtTokenService jwtTokenService;

    @PostMapping("/v1")
    public String payment(@RequestBody Payment payment) {
        return paymentService.doPayment(payment);
    }

    @GetMapping("/v1/{id}")
    public Payment get_payment(@PathVariable int id) {
        return paymentService.getPayment(id);
    }

    @PostMapping("/v1/initiate_payment")
    public String initiate_payment(@RequestParam Integer amount, @RequestParam String currency) {
        try {
            return paymentService.createOrder(amount, currency, "receipt_no");
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

    }
}
