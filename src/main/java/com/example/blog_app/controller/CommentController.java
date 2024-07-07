package com.example.blog_app.controller;

import com.example.blog_app.dto.APIResponse;
import com.example.blog_app.dto.CommentDTO;
import com.example.blog_app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
                                                    @PathVariable Integer postId) {

        CommentDTO createdCommentDTO = commentService.createComment(commentDTO, postId);
        return new ResponseEntity<>(createdCommentDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<APIResponse> deleteComment(@PathVariable Integer commentId) {

        commentService.deleteComment(commentId);
        APIResponse apiResponse = new APIResponse("Comment deleted successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
