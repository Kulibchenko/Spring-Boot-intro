package com.example.springboot.service;

import com.example.springboot.dto.CartItemDto;
import com.example.springboot.dto.CreateCartItemRequestDto;
import com.example.springboot.dto.ShoppingCartDto;
import com.example.springboot.dto.UpdateCartItemRequestDto;
import com.example.springboot.mapper.CartItemMapper;
import com.example.springboot.model.CartItem;
import com.example.springboot.model.ShoppingCart;
import com.example.springboot.repository.book.BookRepository;
import com.example.springboot.repository.cartitem.CartItemRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepository;

    @Override
    public CartItemDto addToCart(CreateCartItemRequestDto requestDto, Long id) {
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        if (bookRepository.findById(cartItem.getBook().getId()).isPresent()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setId(id);
            cartItem.setShoppingCart(shoppingCart);
            return cartItemMapper.toDto(cartItemRepository.save(cartItem));
        }
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public CartItemDto update(Long id, UpdateCartItemRequestDto requestDto) {
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        cartItem.setId(id);
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public ShoppingCartDto getById(Pageable pageable, Long id) {
        Set<CartItemDto> cartItemsDto = cartItemRepository.findAllByShoppingCartId(id).stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toSet());
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(id);
        shoppingCartDto.setCartItemsDto(cartItemsDto);
        return shoppingCartDto;
    }
}
