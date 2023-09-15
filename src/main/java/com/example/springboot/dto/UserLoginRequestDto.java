package com.example.springboot.dto;

import com.example.springboot.lib.ValidEmail;
import jakarta.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @ValidEmail
    @NotBlank(message = "Login can't be null or blank!")
    private String email;
    @Size(min = 8, max = 40)
    @NotBlank(message = "Password can't be null or blank!")
    private String password;
}
