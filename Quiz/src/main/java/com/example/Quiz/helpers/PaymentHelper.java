package com.example.Quiz.helpers;

import com.example.Quiz.models.Payment;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.time.LocalDate;

@Component
public class PaymentHelper {

    public enum PaymentStatus {
        SUCCESS,
        FAILURE,
        PENDING,
        DECLINED
    }

    public enum PaymentType {
        CC,
        DC,
        ECHECK
    }


    public boolean paymentValidator(Payment payment){
        return paymentAmountValidator(payment) && paymentProcessingFeeValidator(payment) && paymentMethodValidator(payment);
    }

    public boolean paymentAmountValidator(Payment payment){
        if(payment.getPayment_amount()>0){
            return true;
        }
        payment.setCode("101");
        payment.setCodeMssg("Amount cannot be null");
        return false;
    }

    public boolean paymentProcessingFeeValidator(Payment payment){
        if(payment.getProcessing_fee()>0){
            return true;
        }
        payment.setCode("101");
        payment.setCodeMssg("Processing Fee cannot be null");
        return false;
    }

    public boolean validateCardExpiry(Payment payment){
        int year = payment.getPaymentMethod().getCcExpiry().getYear(); //example 2025
        int month = payment.getPaymentMethod().getCcExpiry().getMonth(); //example 12
        LocalDate today = LocalDate.now();
        System.out.print("Todays Date: "+today);
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();
        if(currentYear > year){
            return false;
        } else if (currentYear == year && (currentMonth > month) ) {
            return false;
        }
        else{
            return true;
        }
    }

    public boolean validateCardNumber(Payment payment){
        String cardNumber = payment.getPaymentMethod().getAccountNumber();
        if(cardNumber.length()==16){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isCardPayment(Payment payment){
        PaymentType paymentType = PaymentType.valueOf(payment.getPaymentMethod().getMethodType());
        if(paymentType == PaymentType.CC || paymentType == PaymentType.DC){
            return true;
        }
        else{
            return false;
        }
    }


    public boolean paymentMethodValidator(Payment payment){
        // check method type, if gets validated, then check card validation(expiry and length).
        PaymentType paymentType = PaymentType.valueOf(payment.getPaymentMethod().getMethodType());
        boolean valid = true;
        if(paymentType == PaymentType.CC || paymentType == PaymentType.DC || paymentType == PaymentType.ECHECK){ // validation for echeck?
            if(isCardPayment(payment)){
                return validateCardExpiry(payment) && validateCardNumber(payment);
            }
            return true;
        }
        return false;
    }

}
