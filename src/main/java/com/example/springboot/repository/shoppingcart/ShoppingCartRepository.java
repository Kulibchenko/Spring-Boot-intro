package com.example.springboot.repository.shoppingcart;

import com.example.springboot.model.ShoppingCart;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserId(Long id);

    //Optional<ShoppingCart> findALl(Pageable pageable, @Param("id")Long id);
}
