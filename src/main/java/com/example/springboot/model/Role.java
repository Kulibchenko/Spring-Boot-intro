package com.example.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE roles SET is_deleted = true WHERE id =?")
@Where(clause = "is_deleted = false")
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @UniqueElements
    @Enumerated(value = EnumType.STRING)
    private RoleName role;

    public Role(RoleName role) {
        this.role = role;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return role.toString();
    }

    public enum RoleName {
        ROLE_ADMIN,
        ROLE_USER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) && this.role == role.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "" + role;
    }
}
