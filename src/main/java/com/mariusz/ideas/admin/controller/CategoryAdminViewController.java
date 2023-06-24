package com.mariusz.ideas.admin.controller;

import com.mariusz.ideas.category.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("categories", categoryService.getCategory(id));

        return "admin/category/edit";
    }
}
