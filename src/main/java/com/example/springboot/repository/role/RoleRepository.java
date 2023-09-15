package com.example.springboot.repository.role;

import com.example.springboot.model.Role;
import com.example.springboot.model.Role.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RoleName role);
}
