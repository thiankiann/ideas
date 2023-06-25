package com.mariusz.ideas.guestion.service;

import com.mariusz.ideas.guestion.domain.model.Answer;
import com.mariusz.ideas.guestion.domain.model.Question;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {

    public List<Answer> getAnswers(UUID questionId) {
        return Arrays.asList(
                new Answer("Answer 1"),
                new Answer("Answer 2"),
                new Answer("Answer 3"));
    }

    public Answer getAnswer(UUID answerId) {
        return new Answer("Answer " + answerId);
    }

    public Answer createAnswer(UUID answerId, Answer answer) {
        return null;
    }

    public Answer updateAnswer(UUID answerId, Answer answer) {
        return null;
    }

    public void deleteAnswer(UUID answerId) {
    }
}



