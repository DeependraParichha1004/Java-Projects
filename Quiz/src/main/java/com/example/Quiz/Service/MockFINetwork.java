package com.example.Quiz.Service;

import com.example.Quiz.helpers.PaymentHelper;
import com.example.Quiz.models.PaymentMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.Quiz.helpers.PaymentHelper;

import java.util.List;

@Component
public class MockFINetwork {

    @Value("${valid.cardNumber.list:4111111111111111,5454545454545454}")
    private List<String> validcardNumberList;
    @Value("${invalid.cardNumber.list:4917484589897107}")
    private List<String> declinedCardNumberList;

    public PaymentHelper.PaymentStatus processPaymentProcess(PaymentMethod paymentMethod) {
        System.out.print("AAAAAAA---------->"+validcardNumberList.contains(paymentMethod.getAccountNumber()));
        System.out.print("BBBBBBB---------->"+paymentMethod.getAccountNumber());
        if(PaymentHelper.PaymentType.valueOf(paymentMethod.getMethodType())==PaymentHelper.PaymentType.ECHECK){
            return PaymentHelper.PaymentStatus.SUCCESS;
        } else if (validcardNumberList.contains(paymentMethod.getAccountNumber())) {
            return PaymentHelper.PaymentStatus.SUCCESS;
        }
        return PaymentHelper.PaymentStatus.FAILURE;

    }
}
