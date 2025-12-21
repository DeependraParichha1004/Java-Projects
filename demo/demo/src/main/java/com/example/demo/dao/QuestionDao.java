package com.example.demo.dao;

import com.example.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

//    @Query(value="Select * from question q where q.category=:category order by Random() limit :numQ", nativeQuery = true)
    @Query(
            value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ",
            nativeQuery = true
    )
//    List<Question> getQuestionsByCategoryAndNum(String category, Integer numQ);
    List<Question> getQuestionsByCategoryAndNum(
            @Param("category") String category,
            @Param("numQ") Integer numQ
    );
}
