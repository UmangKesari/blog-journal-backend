package com.example.blog_app.services;

import com.example.blog_app.dto.UserDTO;
import com.example.blog_app.entities.User;

import java.util.List;

public interface UserService {

    public UserDTO createUser(UserDTO user);

    public UserDTO updateUser(UserDTO user, Integer userId);

    public UserDTO getUserById(Integer userId);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userId);

}
