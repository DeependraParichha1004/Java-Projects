package com.example.Quiz.Dao;

import com.example.Quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "select * from question q where q.category=:category order by rand() LIMIT :numq", nativeQuery = true)
    List<Question> getRandomQuestionsByCategory(String category, int numq);

    @Query(value="select * from question q join quiz_questions qq on q.id = qq.questions_id where qq.quiz_id=:id;", nativeQuery = true)
    List<Question> getRandomQuestionsByQuizId(int id);

    @Query(value="select * from question where id in (:nums)", nativeQuery = true)
    List<Question> getQuestionsBySelectiveId(ArrayList<Integer> nums);
}
