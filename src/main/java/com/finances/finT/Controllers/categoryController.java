package com.finances.finT.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finances.finT.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class categoryController {

    private final CategoryService categoryService;

    @Autowired
    public categoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/new")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new categoryDTO());
        return "add_category";
    }

    @PostMapping
    public String addCategory(@ModelAttribute("category") categoryDTO categoryDto,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "add_category";
        }
        categoryService.saveCategory(categoryDto);
        return "redirect:/expenses/add?categoryAdded";
    }

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories_list";
    }
}