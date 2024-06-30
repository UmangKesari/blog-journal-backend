package com.example.blog_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotEmpty
    @Size(min = 2, message = "User's name must be of min 2 characters")
    private String name;

    @Email(message = "Invalid email format!!")
    private String email;

    @NotEmpty
    @Size(min =3, max = 8, message = "Password must be min of 3 chars and max of 10 chars !!")
    private String password;

    @NotNull
    private String about;
}
