package com.mariusz.ideas.category.controller;

import com.mariusz.ideas.common.dto.Message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mariusz.ideas.category.domain.model.Category;
import com.mariusz.ideas.category.service.CategoryService;
import com.mariusz.ideas.common.dto.Message;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;


import static com.mariusz.ideas.common.controller.ControllerUtils.paging;

@Controller
@RequestMapping("/admin/categories")
@Slf4j
public class CategoryAdminViewController {

	private final CategoryService categoryService;

	public CategoryAdminViewController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public String indexView(
			@RequestParam(name = "s", required = false) String search,
			@RequestParam(name = "field", required = false, defaultValue = "id") String field,
			@RequestParam(name = "direction", required = false, defaultValue = "asc") String direction,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			Model model
	) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), field);

		String reverseSort = null;
		if ("asc".equals(direction)) {
			reverseSort = "desc";
		} else {
			reverseSort = "asc";
		}

		Page<Category> categoriesPage = categoryService.getCategories(search, pageable);
		model.addAttribute("categoriesPage", categoriesPage);
		model.addAttribute("search", search);
		model.addAttribute("reverseSort", reverseSort);

		paging(model, categoriesPage);

		return "admin/category/index";
	}

	@GetMapping("{id}")
	public String editView(Model model, @PathVariable UUID id) {
		model.addAttribute("category", categoryService.getCategory(id));

		return "admin/category/edit";
	}

	@PostMapping("{id}")
	public String edit(
			@PathVariable UUID id,
			@Valid @ModelAttribute("category") Category category,
			BindingResult bindingResult,
			RedirectAttributes ra,
			Model model

	) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("category", category);
			model.addAttribute("message", Message.error("Błąd zapisu"));
			return "admin/category/edit";
		}

		try {
			categoryService.updateCategory(id, category);
			ra.addFlashAttribute("message", Message.info("Kategoria zapisana"));

		} catch (Exception e) {
			log.error("Error on category.edit", e);
			model.addAttribute("category", category);
			model.addAttribute("message", Message.error("Nieznany błąd zapisu"));
			return "admin/category/edit";
		}

		return "redirect:/admin/categories";
	}

	@GetMapping("{id}/delete")
	public String deleteView(@PathVariable UUID id, RedirectAttributes ra) {
		try {
			categoryService.deleteCategory(id);
			ra.addFlashAttribute("message", Message.info("Kategoria usunięta"));
		} catch (Exception e) {
			log.error("Error on category.delete", e);
			ra.addFlashAttribute("message", Message.error("Nieznany błąd podczas usuwania"));
			return "redirect:/admin/categories";
		}

		return "redirect:/admin/categories";
	}
}
