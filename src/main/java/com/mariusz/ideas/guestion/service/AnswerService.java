package com.mariusz.ideas.guestion.service;

import com.mariusz.ideas.guestion.domain.model.Answer;
import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.domain.repository.AnswerRepository;
import com.mariusz.ideas.guestion.domain.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    private QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional(readOnly = true)
    public List<Answer> getAnswers(UUID questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Transactional(readOnly = true)
    public Answer getAnswer(UUID id) {
        return answerRepository.getById(id);
    }
    @Transactional
    public Answer createAnswer(UUID questionId, Answer answerRequest) {
        Answer answer = new Answer();
        answer.setName(answerRequest.getName());

        Question question = questionRepository.getReferenceById(questionId);
        question.addAnswer(answer);

        answerRepository.save(answer);
        questionRepository.save(question);

        return answer;
    }

    public Answer updateAnswer(UUID answerId, Answer answerRequest) {
        Answer answer = answerRepository.getById(answerId);
        answer.setName(answerRequest.getName());

        return answerRepository.save(answer);
    }

    public void deleteAnswer(UUID answerId) {
        answerRepository.deleteById(answerId);
    }
}



