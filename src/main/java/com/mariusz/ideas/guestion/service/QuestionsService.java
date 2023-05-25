package com.mariusz.ideas.guestion.service;

import com.mariusz.ideas.guestion.domain.model.Question;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionsService {

    public List<Question> getQuestions() {
        return Arrays.asList(
                new Question("Question 1"),
                new Question("Question 2")
        );
    }

    public Question getQuestion(UUID id) {
        return null;
    }

    public Question createQuestion(Question question) {
        return null;
    }

    public Question updateQuestion(UUID id, Question question) {
        return null;
    }

    public void deleteQuestion(UUID id) {
    }
}
