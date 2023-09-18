package com.example.springboot.controller;

import com.example.springboot.dto.UserLoginRequestDto;
import com.example.springboot.dto.UserLoginResponseDto;
import com.example.springboot.dto.UserRegistrationRequestDto;
import com.example.springboot.dto.UserRegistrationResponseDto;
import com.example.springboot.security.AuthenticationService;
import com.example.springboot.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping(value = "/login")
    public UserLoginResponseDto login(@RequestBody @Valid
                                      UserLoginRequestDto requestDto) {

        return authenticationService.authenticate(requestDto);
    }

    @PostMapping(value = "/register")
    public UserRegistrationResponseDto register(@RequestBody @Valid
             UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }
}
