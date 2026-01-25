package com.example.Quiz.Dao;

import com.example.Quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {


}
