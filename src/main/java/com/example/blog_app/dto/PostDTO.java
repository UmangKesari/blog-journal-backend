package com.example.blog_app.dto;

import com.example.blog_app.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private Set<CommentDTO> comments = new HashSet<>();
}
