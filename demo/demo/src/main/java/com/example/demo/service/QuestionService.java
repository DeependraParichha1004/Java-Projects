package com.example.demo.service;

import com.example.demo.Question;
import com.example.demo.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questiondao;

    public List<Question> getAllQuestions() {
        return questiondao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questiondao.findByCategory(category);
    }
}
