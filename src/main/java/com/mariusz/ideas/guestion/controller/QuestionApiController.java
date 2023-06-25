package com.mariusz.ideas.guestion.controller;

import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.service.QuestionService;
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
@RequestMapping("/api/v1/questions")
public class QuestionApiController {

    private final QuestionService questionService;

    public QuestionApiController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    @GetMapping("{id}")
    Question getQuestion(@PathVariable UUID id) {
        return questionService.getQuestion(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{id}")
    Question updateQuestion(@PathVariable UUID id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }
   @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void deleteQuestion(@PathVariable UUID id){
        questionService.deleteQuestion(id);
    }
}
