package com.example.blog_app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Integer postId;

    private String title;

    private String content;

    private String imageName="default.png";

    private Date date;

    private CategoryDTO category;

    private UserDTO user;
}
