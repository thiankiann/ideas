package com.mariusz.ideas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminViewController {

    @GetMapping
    public String indexView(){
        return "admin/category/index";
    }
}
