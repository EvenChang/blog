package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findForId(Long id);

    Category save(Category category);

    void delete(Category category);

    List<Category> getCategorys();
}
