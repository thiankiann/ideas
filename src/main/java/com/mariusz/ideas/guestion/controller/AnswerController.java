package com.mariusz.ideas.guestion.controller;

import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.service.QuestionsService;
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

    private final QuestionsService questionsService;

    public AnswerController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping
    List<Question> getQuestions() {
        return questionsService.getQuestions();
    }

    @GetMapping("{id}")
    Question getQuestion(@PathVariable UUID id) {
        return questionsService.getQuestion(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Question createQuestion(@RequestBody Question question){
        return questionsService.createQuestion(question);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{id}")
    Question updateQuestion(@PathVariable UUID id, @RequestBody Question question){
        return questionsService.updateQuestion(id, question);
    }
   @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void deleteQuestion(@PathVariable UUID id){
        questionsService.deleteQuestion(id);
    }
}
