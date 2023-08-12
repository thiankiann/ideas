package com.mariusz.ideas.category.controller;

import com.mariusz.ideas.common.controller.IdeasCommonViewController;
import com.mariusz.ideas.guestion.domain.model.Question;
import com.mariusz.ideas.guestion.service.QuestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mariusz.ideas.category.domain.model.Category;

import com.mariusz.ideas.category.service.CategoryService;


import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryViewController extends IdeasCommonViewController {

	private final CategoryService categoryService;
	private final QuestionService questionService;

	@GetMapping("{id}")
	public String singleView(@PathVariable UUID id, Model model){
		Category category = categoryService.getCategory(id);
		List<Question> questions = questionService.findAllByCategoryId(id);

		model.addAttribute("category", category);
		model.addAttribute("questions", questions);
		addGlobalAttributes(model);

		return "category/single";
	}
}
