package com.example.springboot.mapper;

import com.example.springboot.config.MapperConfig;
import com.example.springboot.dto.UserRegistrationRequestDto;
import com.example.springboot.dto.UserRegistrationResponseDto;
import com.example.springboot.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RegistrationUserMapper {
    UserRegistrationResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
