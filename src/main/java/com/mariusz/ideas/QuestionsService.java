package com.mariusz.ideas;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionsService {

    public List<Question> getQuestions() {
        return Arrays.asList(
                new Question("Question 1"),
                new Question("Question 2")
        );
    }
}
