package com.example.demo.service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static tools.jackson.databind.cfg.CoercionInputShape.Array;

//import static tools.jackson.databind.type.LogicalType.Array;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;



    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        List<Question> questions = questionDao.getQuestionsByCategoryAndNum(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz Created Successfully!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer id){
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> qw = new ArrayList<>();
        for(Question q : questions){
            QuestionWrapper wrapper = new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(),q.getOption2(), q.getOption3(), q.getOption4());
            qw.add(wrapper);
        }
        return new ResponseEntity<>(qw, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitAnswers(Integer id, List<Response> responses) {
        List<Question> questions = quizDao.findById(id).get().getQuestions();
        int right = 0;
        int i =0;
        for(Response r : responses){
            if(r.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);

    }
}
