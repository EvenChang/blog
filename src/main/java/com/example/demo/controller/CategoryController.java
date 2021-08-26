package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Post;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/newCategory")
    public String newCategory(Model model) {

        Category category = new Category();
        List<Category> categoryList = categoryService.getCategorys();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("category", category);

        return "/categoryForm";
    }

    @PostMapping("/newCategory")
    public String createNewCategory(@ModelAttribute Category category) {
        categoryService.save(category);

        return "redirect:/newCategory";
    }

    @RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
    public String deleteCategory(@ModelAttribute Category category) {

        categoryService.findForId(category.getId())
                .ifPresent(categoryService::delete);

        return "redirect:/newCategory";
    }
}
