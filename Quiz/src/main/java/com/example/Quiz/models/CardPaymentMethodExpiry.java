package com.example.Quiz.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
public class CardPaymentMethodExpiry {
    private int year;
    private int month;

}

