package com.example.Quiz.Dao;

import com.example.Quiz.Question;
import com.example.Quiz.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {

    Payment findById(int id);
}
