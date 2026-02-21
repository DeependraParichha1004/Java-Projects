package com.example.Quiz.Service;

import com.example.Quiz.Dao.PaymentDao;
import com.example.Quiz.helpers.PaymentHelper;
import com.example.Quiz.models.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Order;

import io.lettuce.core.json.JsonObject;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties.Json;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {

    @Value("${razorpay.api_key}")
    private String api_key;

    @Value("${razorpay.secret_key}")
    private String secret_key;

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    PaymentHelper paymentHelper;

    @Autowired
    MockFINetwork mockFINetwork;

    public String doPayment(Payment payment) {
        if (paymentHelper.paymentValidator(payment)) {
            payment.setPayment_date(new Date());
            payment.setPayment_status(mockFINetwork.processPaymentProcess(payment.getPaymentMethod()).toString());
            paymentDao.save(payment);
            return "Payment record added successfully!";
        } else {
            return "Payment couldn't be added successfully!";
        }
    }

    @Cacheable(value = "payments", key = "#id")
    public Payment getPayment(int id) {
        return paymentDao.findById(id);
    }

    public String createOrder(int amount, String currency, String receipt_no) throws RazorpayException {
        RazorpayClient rc = new RazorpayClient(api_key, secret_key);
        JSONObject jb = new JSONObject();
        jb.put("amount", amount * 100);
        jb.put("currency", currency);
        jb.put("receipt_no", receipt_no);

        Order or = rc.orders.create(jb);
        return or.toString();
    }

}
