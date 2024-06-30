package com.example.blog_app.controller;

import com.example.blog_app.dto.APIResponse;
import com.example.blog_app.dto.CategoryDTO;
import com.example.blog_app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                      @PathVariable Integer categoryId) {
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO, categoryId);

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        APIResponse apiResponse = new APIResponse("Category deleted successfully!!", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOs = categoryService.getCategories();
        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);

    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId) {
        CategoryDTO categoryDTO = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);

    }
}
