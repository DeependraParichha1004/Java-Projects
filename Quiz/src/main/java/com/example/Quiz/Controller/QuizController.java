package com.example.Quiz.Controller;

import com.example.Quiz.Question;
import com.example.Quiz.QuestionWrapper;
import com.example.Quiz.Response;
import com.example.Quiz.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> addQuiz(@RequestParam String category, @RequestParam Integer numq, @RequestParam String title){
        return quizService.createQuiz(category, numq, title);
    }

    // get quiz by id
    // 1st approach
//    @GetMapping("/list/{id}")
//    public List<Question> getQuestionsByQuizId1(@PathVariable Integer id){
//        return quizService.getRandomQuestionsByQuizId1(id);
//    }

    // 2nd Approach
    @GetMapping("/list/{id}")
    public List<QuestionWrapper> getQuestionsByQuizId2(@PathVariable Integer id){
        return quizService.getRandomQuestionsByQuizId2(id);
    }

    @PostMapping("/submit/{id}")
    public Integer submitAnswer(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.submitAnswers(id, responses);
    }
}
