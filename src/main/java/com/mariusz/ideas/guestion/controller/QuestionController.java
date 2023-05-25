package com.mariusz.ideas.guestion.controller;

import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.service.QuestionsService;
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
@RequestMapping("questions")
public class QuestionController {

    private final QuestionsService questionsService;

    public QuestionController(QuestionsService questionsService) {
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
    Question createQuestion(@RequestBody Question question){
        return questionsService.createQuestion(question);
    }

    @PutMapping("{id}")
    Question updateQuestion(@PathVariable UUID id, @RequestBody Question question){
        return questionsService.updateQuestion(id, question);
    }

    @DeleteMapping("{id}")
    void deleteQuestion(@PathVariable UUID id){
        questionsService.deleteQuestion(id);
    }
}
