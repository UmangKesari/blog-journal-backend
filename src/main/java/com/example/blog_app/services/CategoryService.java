package com.example.blog_app.services;

import com.example.blog_app.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    public void deleteCategory(Integer categoryId);

    public CategoryDTO getCategory(Integer categoryId);

    public List<CategoryDTO> getCategories();
}
