package com.mariusz.ideas.guestion.service;

import com.mariusz.ideas.category.domain.repository.CategoryRepository;
import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.domain.repository.AnswerRepository;
import com.mariusz.ideas.guestion.domain.repository.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceIT {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    void shouldGetAllQuestions() {

        //given
        questionRepository.deleteAll();

        questionRepository.saveAll(List.of(
                new Question("Question1"),
                new Question("Question2"),
                new Question("Question3")
        ));

        //when
        List<Question> questions = questionService.getQuestions();

        //then
        assertThat(questions)
                .hasSize(3)
                .extracting(Question::getName)
                .containsExactlyInAnyOrder("Question1", "Question2", "Question3");
    }

    @Test
    void shouldSingleGetQuestion() {
        //given
        Question question = new Question("Question2");

        questionRepository.saveAll(List.of(
                new Question("Question1"),
                question,
                new Question("Question3")
        ));
        //when
        Question result = questionService.getQuestion(question.getId());

        //then
        assertThat(result.getId()).isEqualTo(question.getId());
    }

    @Test
    void shouldCreateQuestion() {
        // given
        Question question = new Question("Question");

        // when
        Question result = questionService.createQuestion(question);

        // then
        assertThat(result.getName()).isEqualTo(question.getName());
    }

    @Test
    void updateQuestion() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void findByCategoryId() {
    }

    @Test
    void findHot() {
    }

    @Test
    void findUnanswered() {
    }

    @Test
    void findByQuery() {
    }
}