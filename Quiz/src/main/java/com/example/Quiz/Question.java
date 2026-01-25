package com.example.Quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //differencr between identity and sequence?
    private Long id;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String right_answer;
    private String question_title;
    private String difficult_level;
    private String category;
}
