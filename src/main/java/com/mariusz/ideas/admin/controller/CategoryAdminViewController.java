package com.mariusz.ideas.admin.controller;

import com.mariusz.ideas.category.service.CategoryService;
import com.mariusz.ideas.guestion.domain.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String edit(@ModelAttribute("category")Category category, @PathVariable UUID id){

       categoryService.updateCategory(id,category);

        return "admin/category/categories";
    }
}
