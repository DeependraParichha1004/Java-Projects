package com.example.Quiz.Controller;

import com.example.Quiz.Question;
import com.example.Quiz.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public List<Question> get_questions() {
        return questionService.getQuestions();
    }

    // path variable -> "/question/allQuestions/category/java"
    // @GetMapping("allQuestions/category/{category}")
    // public List<Question> get_questions_by_category(@PathVariable String
    // category){
    // return questionService.getQuestionsByCategory(category);
    // }

    // query params -> "/question/allQuestions/category?category=java"
    @GetMapping("/allQuestions/category")
    public ResponseEntity<List<Question>> get_questions_by_category(@RequestParam String category) {
        if (questionService.getQuestionsByCategory(category) != null) {
            return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add_question(@RequestBody Question question) {
        return questionService.addQuestions(question);
    }
}
