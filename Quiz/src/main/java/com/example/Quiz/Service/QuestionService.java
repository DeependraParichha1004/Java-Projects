package com.example.Quiz.Service;

import com.example.Quiz.Dao.QuestionDao;
import com.example.Quiz.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questiondao;

    public List<Question> getQuestions() {
        return questiondao.findAll();
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questiondao.findByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
    }

    public ResponseEntity<String> addQuestions(Question question){
        try{
            questiondao.save(question);
            return new ResponseEntity<>("Question Added Successfully!", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question Cannot be saved!", HttpStatus.BAD_REQUEST);
    }
}
