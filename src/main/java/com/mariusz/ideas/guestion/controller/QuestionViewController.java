package com.mariusz.ideas.guestion.controller;

import com.mariusz.ideas.category.service.CategoryService;
import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.service.AnswerService;
import com.mariusz.ideas.guestion.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.mariusz.ideas.common.controller.ControllerUtils.paging;

@Controller
@RequestMapping("/questions")
public class QuestionViewController {

    private final QuestionService questionService;
    private final CategoryService categoryService;
    private final AnswerService answerService;

    public QuestionViewController(QuestionService questionService, CategoryService categoryService, AnswerService answerService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
        this.answerService = answerService;
    }

    @GetMapping
    public String indexView(Model model){
        model.addAttribute("questions", questionService.getQuestions());
        model.addAttribute("categories", categoryService.getCategories(
                PageRequest.of(0,10, Sort.by("name").ascending())
        ));

        return "question/index";
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id){
        model.addAttribute("question", questionService.getQuestion(id));
        model.addAttribute("categories", categoryService.getCategories(Pageable.unpaged()));
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
        questionService.createQuestion(question);

        return "redirect:/questions";
    }
    @GetMapping("hot")
    public String hotView(
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model){

        PageRequest pageRequest = PageRequest.of(page -1, 2);

        Page<Question> questionsPage = questionService.findHot(pageRequest);

        model.addAttribute("questionPage",questionsPage);
        paging(model,questionsPage);





        return "question/index";
    }
}
