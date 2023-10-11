package com.example.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Login can't be null or blank!")
    private String email;

    @Size(min = 8, max = 40)
    @NotBlank(message = "Password can't be null or blank!")
    private String password;
}
