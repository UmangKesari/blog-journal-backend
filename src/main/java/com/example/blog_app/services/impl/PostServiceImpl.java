package com.example.blog_app.services.impl;

import com.example.blog_app.dto.PostDTO;
import com.example.blog_app.dto.PostPaginatedResponse;
import com.example.blog_app.entities.Category;
import com.example.blog_app.entities.Post;
import com.example.blog_app.entities.User;
import com.example.blog_app.exceptions.ResourceNotFoundException;
import com.example.blog_app.repository.CategoryRepository;
import com.example.blog_app.repository.PostRepository;
import com.example.blog_app.repository.UserRepository;
import com.example.blog_app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post createdPost = postRepository.save(post);
        return modelMapper.map(createdPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        post.setImageName(postDTO.getImageName());

        Post updatedPost = postRepository.save(post);

        return modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        postRepository.delete(post);

    }

    @Override
    public PostPaginatedResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepository.findAll(pageRequest);

        List<Post> allPosts = pagePost.getContent();

        //List<Post> posts = postRepository.findAll();

        List<PostDTO> postDTOs = allPosts.stream().map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        PostPaginatedResponse postPaginatedResponse = new PostPaginatedResponse();
        postPaginatedResponse.setContent(postDTOs);
        postPaginatedResponse.setPageNumber(pagePost.getNumber());
        postPaginatedResponse.setPageSize(pagePost.getSize());
        postPaginatedResponse.setTotalElements(pagePost.getTotalElements());
        postPaginatedResponse.setTotalPages(pagePost.getTotalPages());
        postPaginatedResponse.setLastPage(pagePost.isLast());

        return postPaginatedResponse;
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", id));

        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        return postDTO;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

        List<Post> posts = postRepository.findByCategory(category);
        List<PostDTO> postDTOs = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return postDTOs;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

        List<Post> posts = postRepository.findByUser(user);
        List<PostDTO> postDTOs = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postDTOs;
    }
}
