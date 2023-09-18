package com.example.springboot.controller;

import com.example.springboot.dto.UserLoginRequestDto;
import com.example.springboot.dto.UserLoginResponseDto;
import com.example.springboot.dto.UserRegistrationRequestDto;
import com.example.springboot.dto.UserRegistrationResponseDto;
import com.example.springboot.security.AuthenticationService;
import com.example.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication controller", description = "Endpoints for authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Operation(summary = "Login", description = "Authorization endpoint")
    @PostMapping(value = "/login")
    public UserLoginResponseDto login(@RequestBody @Valid
                                      UserLoginRequestDto requestDto) {

        return authenticationService.authenticate(requestDto);
    }

    @Operation(summary = "Registration", description = "Registration endpoint")
    @PostMapping(value = "/register")
    public UserRegistrationResponseDto register(@RequestBody @Valid
             UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }
}
