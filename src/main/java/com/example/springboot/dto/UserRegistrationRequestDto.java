package com.example.springboot.dto;

import com.example.springboot.lib.FieldsValueMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRegistrationRequestDto {
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, max = 40)
    private String password;

    private String repeatPassword;
    private String firstName;
    private String lastName;
    private String shippingAddress;
}
