package com.example.demo.controller;

import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Response;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title)
    {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("getQuiz/{Id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable Integer Id){
        return quizService.getQuestions(Id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitAnswers(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.submitAnswers(id, responses);
    }
}
