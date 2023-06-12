package com.mariusz.ideas.guestion.controller;

import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.service.QuestionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/questions")
public class QuestionViewController {

    private final QuestionsService questionsService;

    public QuestionViewController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping
    public String indexView(Model model){
        model.addAttribute("questions", questionsService.getQuestions());

        return "question/index"; 
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id){
        model.addAttribute("question", questionsService.getQuestion(id));
        return "question/single";
    }
    @GetMapping("add")
    public String addView(Model model){
        model.addAttribute("question", new Question());

        return "question/add";
    }
    @PostMapping
    public String add(Question question){
        questionsService.createQuestion(question);

        return "redirect:/questions";
    }
}
