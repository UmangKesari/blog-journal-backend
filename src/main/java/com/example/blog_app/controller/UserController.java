package com.example.blog_app.controller;

import com.example.blog_app.dto.APIResponse;
import com.example.blog_app.dto.UserDTO;
import com.example.blog_app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser( @Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUserDTO = userService.createUser(userDTO);


        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
        /**return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUserDTO)
                .build();*/
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer userId) {
        UserDTO updatedUserDTO = userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new APIResponse( "User deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        System.out.println("Received user id " + userId);
        UserDTO userDTO = userService.getUserById(userId);


        return ResponseEntity.ok(userDTO);
    }

}
