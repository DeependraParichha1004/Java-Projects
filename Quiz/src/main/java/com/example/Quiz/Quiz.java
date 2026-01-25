package com.example.Quiz;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //differencr between identity and sequence?
    private Long id;
    private String title;

    @ManyToMany
    private List<Question> questions;
}
