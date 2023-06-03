package com.mariusz.ideas.guestion.controller;

import com.mariusz.ideas.guestion.domain.model.Answer;
import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * C -create
        ->POST/questions

 * R -read
        ->GET/questions           - for all questions
        ->GET/questions/[id]      - for a single question

 * U -update
        ->PUT/questions/[id]

 * D -delete
        -> DELETE/questions/[id]
 */

@RestController
@RequestMapping("/api/v1/questions/{question-id}/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    List<Answer> getAnswers(@PathVariable("question-id") UUID questionId) {
        return answerService.getAnswers(questionId);
    }

    @GetMapping("{answer-id}")
    Answer getAnswer(@PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerId) {
        return answerService.getAnswer(answerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Answer createAnswer(@PathVariable("question-id") UUID questionId , @RequestBody Answer answer){
        return answerService.createAnswer(questionId, answer);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{answer-id}")
    Answer updateAnswer(@PathVariable("question-id") UUID questionId, @PathVariable ("answer-id") UUID answerId,
                        @RequestBody Answer answer){
        return answerService.updateAnswer(answerId, answer);
    }

   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("{answer-id}")
    void deleteAnswer(@PathVariable("answer-id") UUID answerId){
       answerService.deleteAnswer(answerId);
    }
}
