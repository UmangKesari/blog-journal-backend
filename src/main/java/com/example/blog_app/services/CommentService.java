package com.example.blog_app.services;

import com.example.blog_app.dto.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Integer postId);

    void deleteComment(Integer commentId);
}
