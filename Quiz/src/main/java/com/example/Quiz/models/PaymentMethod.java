package com.example.Quiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
public class PaymentMethod {
    private String id;
    private String accountNumber;
    private String methodType;
    private String cardHolderName;
    private String description;
    private String routingNumber;
    private String fiName;
    private String activeFlag;
    private CardPaymentMethodExpiry ccExpiry;

}
