package com.example.blog_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer categoryId;

    @NotEmpty
    @Size(min =4, message = "Category Title should contain atleast 4 chars")
    private String categoryTitle;

    @NotEmpty
    @Size(min =4, message = "Category description should contain atleast 4 chars")
    private String categoryDescription;
}
