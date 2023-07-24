package com.mariusz.ideas.category.controller;

import com.mariusz.ideas.common.dto.Message;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mariusz.ideas.category.domain.model.Category;
import com.mariusz.ideas.category.service.CategoryService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminViewController {

	private final CategoryService categoryService;

	public CategoryAdminViewController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public String indexView(Model model){
		model.addAttribute("categories", categoryService.getCategories());

		return "admin/category/index";
	}

	@GetMapping("{id}")
	public String editView(Model model, @PathVariable UUID id){
		model.addAttribute("category", categoryService.getCategory(id));

		return "admin/category/edit";
	}

	@PostMapping("{id}")
	public String edit(@PathVariable UUID id,
					   @Valid @ModelAttribute("category") Category category,
					   BindingResult bindingResult,
					   RedirectAttributes ra,
					   Model model
					   ){

		if(bindingResult.hasErrors()){
			model.addAttribute("category", category);
			model.addAttribute("message", Message.error("Error Saving"));
			return "admin/category/edit";
		}
		try {
			categoryService.updateCategory(id, category);
			ra.addFlashAttribute("message", Message.info("Category Saved"));
		} catch (Exception e) {
			model.addAttribute("category", category);
			model.addAttribute("message", Message.error("Unknown Error Saving"));
			return "admin/category/edit";
		}

		return "redirect:/admin/categories";
	}

	@GetMapping("{id}/delete")
	public String deleteView(@PathVariable UUID id, RedirectAttributes ra){

		categoryService.deleteCategory(id);
		ra.addFlashAttribute(Message.info("Category deleted"));

		return "redirect:/admin/categories";
	}
}
