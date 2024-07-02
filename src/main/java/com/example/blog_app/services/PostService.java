package com.example.blog_app.services;

import com.example.blog_app.dto.PostDTO;
import com.example.blog_app.dto.PostPaginatedResponse;
import com.example.blog_app.entities.Post;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    PostDTO updatePost(PostDTO postDTO, Integer postId);

    void deletePost(Integer postId);

    PostPaginatedResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    PostDTO getPostById(Integer id);

    List<PostDTO> getPostsByCategory(Integer categoryId);

    List<PostDTO> getPostsByUser(Integer userId);

}
