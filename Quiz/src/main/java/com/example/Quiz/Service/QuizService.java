package com.example.Quiz.Service;

import com.example.Quiz.Dao.QuestionDao;
import com.example.Quiz.Dao.QuizDao;
import com.example.Quiz.Question;
import com.example.Quiz.QuestionWrapper;
import com.example.Quiz.Quiz;
import com.example.Quiz.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numq, String title) {
        List<Question> questions = questionDao.getRandomQuestionsByCategory(category, numq);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz Added Successfully!", HttpStatus.CREATED);
    }

    public List<Question> getRandomQuestionsByQuizId1(Integer id) {
        List<Question> questions = questionDao.getRandomQuestionsByQuizId(id);
        return questions;
    }

    public List<QuestionWrapper> getRandomQuestionsByQuizId2(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> forUser = new ArrayList<>();

        for(Question q : questions){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(), q.getOption4());
            forUser.add(qw);
        }

        return forUser;
    }

    public Integer submitAnswers(Integer id, List<Response> responses) {
        List<Integer> question_ids = new ArrayList<>();
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
//        System.out.print(questions);
        int i = 0;
//        for(Response response : responses){
//            question_ids.add(response.getId());
//        }
        int right =0;
        for(Response response : responses){
            System.out.print("response: -> "+response.getResponse());
//            System.out.print("right answer: -> "+questions.get(i).getRight_answer());
            System.out.print("right answer: -> "+questions.get(i).getId());
            if(response.getResponse().equals(questions.get(i).getRight_answer())){

                right ++;
            }
            i++;
        }
        return right;
    }

}
