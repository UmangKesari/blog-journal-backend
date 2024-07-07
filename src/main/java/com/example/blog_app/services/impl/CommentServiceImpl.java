package com.example.blog_app.services.impl;

import com.example.blog_app.dto.CommentDTO;
import com.example.blog_app.entities.Comment;
import com.example.blog_app.entities.Post;
import com.example.blog_app.exceptions.ResourceNotFoundException;
import com.example.blog_app.repository.CommentRepository;
import com.example.blog_app.repository.PostRepository;
import com.example.blog_app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));


        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setPostId(post);
        Comment createdComment = commentRepository.save(comment);

        return modelMapper.map(createdComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "comment Id", commentId));
        commentRepository.delete(comment);
    }
}
