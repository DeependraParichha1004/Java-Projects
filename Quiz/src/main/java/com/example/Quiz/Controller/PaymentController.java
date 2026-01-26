package com.example.Quiz.Controller;

import com.example.Quiz.models.Payment;
import com.example.Quiz.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/v1")
    public String payment(@RequestBody Payment payment){
        return paymentService.doPayment(payment) ;
    }

    @GetMapping("/v1/{id}")
    public Payment get_payment(@PathVariable int id){
        return paymentService.getPayment(id);
    }
}
