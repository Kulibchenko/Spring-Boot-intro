package com.example.springboot.service;

import com.example.springboot.dto.UserRegistrationRequestDto;
import com.example.springboot.dto.UserRegistrationResponseDto;
import com.example.springboot.exception.RegistrarionException;
import com.example.springboot.mapper.RegistrationUserMapper;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.repository.user.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationUserMapper userMapper;
    private final RoleService roleService;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrarionException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrarionException("Unable to complete registration");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setShippingAddress(request.getShippingAddress());
        if (request.getEmail().startsWith("admin@")) {
            user.setRoles(List.of(roleService.findByName(Role.RoleName.ROLE_ADMIN)));
        } else {
            user.setRoles(List.of(roleService.findByName(Role.RoleName.ROLE_USER)));
        }
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
