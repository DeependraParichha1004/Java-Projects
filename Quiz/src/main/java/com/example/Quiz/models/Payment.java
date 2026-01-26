package com.example.Quiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;
    private Long reference_id;
    private Double payment_amount;
    private Double processing_fee;
    private String merchant_ref_num;
    @Transient
    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;
    private String payment_type_description;
    private String payment_status;
    private Date payment_date;
    private String errorCode;
    private String errorMssg;

    public void setCode(String code){
        this.errorCode = code;
    }

    public void setCodeMssg(String mssg){
        this.errorMssg = mssg;
    }

}
