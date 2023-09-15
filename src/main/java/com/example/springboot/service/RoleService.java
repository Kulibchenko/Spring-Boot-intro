package com.example.springboot.service;

import com.example.springboot.model.Role;
import com.example.springboot.model.Role.RoleName;

public interface RoleService {
    Role add(Role role);

    Role findByName(RoleName name);
}

