package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Post;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PostService;
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
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;

    @Autowired
    public PostController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping("/newPost")
    public String newPost(Model model) {

        Post post = new Post();

        List<Category> categoryList = categoryService.getCategorys();

        model.addAttribute("post", post);
        model.addAttribute("categoryList", categoryList);
        return "/postForm";
    }

    @PostMapping("/newPost")
    public String createNewPost(@ModelAttribute Post post) {
        postService.save(post);

        return "redirect:/home";
    }

    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.POST)
    public String deletePost(@PathVariable Long id) {

        Optional<Post> post = postService.findForId(id);
        post.ifPresent(postService::delete);

        return "redirect:/home";
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable Long id, Model model) {

        Optional<Post> optionalPost = postService.findForId(id);
        optionalPost.ifPresent(post -> {
            model.addAttribute("post", post);
        });

        return "/editForm";
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.POST)
    public String editPost(@ModelAttribute Post post) {

        postService.save(post);

        return "redirect:/home";
    }

}
