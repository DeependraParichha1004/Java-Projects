package com.example.Quiz.Service;

import com.example.Quiz.Dao.QuestionDao;
import com.example.Quiz.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questiondao;

    public List<Question> getQuestions() {
        return questiondao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questiondao.findByCategory(category);
    }
}
