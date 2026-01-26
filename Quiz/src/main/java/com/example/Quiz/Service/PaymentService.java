package com.example.Quiz.Service;

import com.example.Quiz.Dao.PaymentDao;
import com.example.Quiz.helpers.PaymentHelper;
import com.example.Quiz.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    PaymentHelper paymentHelper;

    @Autowired
    MockFINetwork mockFINetwork;

    public String doPayment(Payment payment){
        if(paymentHelper.paymentValidator(payment)){
            payment.setPayment_date(new Date());
            payment.setPayment_status(mockFINetwork.processPaymentProcess(payment.getPaymentMethod()).toString());
            paymentDao.save(payment);
            return "Payment record added successfully!";
        }
        else{
            return "Payment couldn't be added successfully!";
        }
    }

    public Payment getPayment(int id){
        return paymentDao.findById(id);
    }

}
