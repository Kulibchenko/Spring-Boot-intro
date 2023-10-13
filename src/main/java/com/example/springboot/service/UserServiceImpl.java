package com.example.springboot.service;

import com.example.springboot.dto.UserRegistrationRequestDto;
import com.example.springboot.dto.UserRegistrationResponseDto;
import com.example.springboot.exception.RegistrationException;
import com.example.springboot.mapper.RegistrationUserMapper;
import com.example.springboot.model.Role;
import com.example.springboot.model.ShoppingCart;
import com.example.springboot.model.User;
import com.example.springboot.repository.shoppingcart.ShoppingCartRepository;
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
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration, user already exist");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setShippingAddress(request.getShippingAddress());
        user.setRoles(List.of(roleService.findByName(Role.RoleName.ROLE_USER)));
        User savedUser = userRepository.save(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(savedUser.getId());
        shoppingCartRepository.save(shoppingCart);
        return userMapper.toDto(savedUser);
    }
}
