package com.example.blog_app.services.impl;

import com.example.blog_app.dto.CategoryDTO;
import com.example.blog_app.entities.Category;
import com.example.blog_app.exceptions.ResourceNotFoundException;
import com.example.blog_app.repository.CategoryRepository;
import com.example.blog_app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);

        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        Category updatedCategory = categoryRepository.save(category);

        return modelMapper.map(updatedCategory,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return categoryDTOs;
    }
}
