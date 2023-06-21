package com.mariusz.ideas.guestion.controller;

import com.mariusz.ideas.category.service.CategoryService;
import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.service.AnswerService;
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
    private final CategoryService categoryService;
    private final AnswerService answerService;

    public QuestionViewController(QuestionsService questionsService, CategoryService categoryService, AnswerService answerService) {
        this.questionsService = questionsService;
        this.categoryService = categoryService;
        this.answerService = answerService;
    }

    @GetMapping
    public String indexView(Model model){
        model.addAttribute("questions", questionsService.getQuestions());
        model.addAttribute("categories", categoryService.getCategories());

        return "question/index";
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id){
        model.addAttribute("question", questionsService.getQuestion(id));
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("answers", answerService.getAnswers(id));

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
