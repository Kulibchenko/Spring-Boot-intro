package com.example.springboot.service;

import com.example.springboot.model.Role;
import com.example.springboot.model.Role.RoleName;
import com.example.springboot.repository.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(RoleName role) {
        return roleRepository.findByRole(role).get();
    }
}
