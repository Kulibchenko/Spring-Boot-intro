package com.example.springboot.service;

import com.example.springboot.dto.UserRegistrationRequestDto;
import com.example.springboot.dto.UserRegistrationResponseDto;
import com.example.springboot.exception.RegistrarionException;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrarionException;
}
