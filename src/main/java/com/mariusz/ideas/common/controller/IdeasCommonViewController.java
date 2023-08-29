package com.mariusz.ideas.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import com.mariusz.ideas.category.service.CategoryService;

public abstract class IdeasCommonViewController {

	@Autowired
	protected CategoryService categoryService;

	protected void addGlobalAttributes(Model model) {
		model.addAttribute("categoriesTop", categoryService.getCategories(
				PageRequest.of(0, 10, Sort.by("name").ascending())
		));
	}
}
