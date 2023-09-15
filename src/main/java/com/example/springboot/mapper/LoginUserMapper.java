package com.example.springboot.mapper;

import com.example.springboot.config.MapperConfig;
import com.example.springboot.dto.UserLoginRequestDto;
import com.example.springboot.dto.UserLoginResponseDto;
import com.example.springboot.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface LoginUserMapper {
    UserLoginResponseDto toDto(User user);

    User toModel(UserLoginRequestDto requestDto);
}
