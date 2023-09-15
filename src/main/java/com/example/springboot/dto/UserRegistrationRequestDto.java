package com.example.springboot.dto;

import com.example.springboot.lib.FieldsValueMatch;
import com.example.springboot.lib.ValidEmail;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRegistrationRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
    private String firstName;
    private String lastName;
    private String shippingAddress;
}
