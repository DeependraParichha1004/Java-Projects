package com.example.Quiz.Controller;

import com.example.Quiz.Question;
import com.example.Quiz.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public List<Question> get_questions(){
        return questionService.getQuestions();
    }

//    @GetMapping("allQuestions/category/{category}")
//    public List<Question> get_questions_by_category(@PathVariable String category){
//        return questionService.getQuestionsByCategory(category);
//    }

    //query params
    @GetMapping("/allQuestions/category")
    public List<Question> get_questions_by_category(@RequestParam("name") String category){
        return questionService.getQuestionsByCategory(category);
    }
}
