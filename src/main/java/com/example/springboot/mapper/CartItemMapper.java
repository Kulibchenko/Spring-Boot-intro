package com.example.springboot.mapper;

import com.example.springboot.config.MapperConfig;
import com.example.springboot.dto.BookDto;
import com.example.springboot.dto.CartItemDto;
import com.example.springboot.dto.CreateCartItemRequestDto;
import com.example.springboot.dto.UpdateCartItemRequestDto;
import com.example.springboot.model.Book;
import com.example.springboot.model.CartItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);

    CartItem toModel(CreateCartItemRequestDto requestDto);

    CartItem toModel(UpdateCartItemRequestDto requestDto);

    @AfterMapping
    default void setBookIds(@MappingTarget CartItemDto cartItemDto, CartItem cartItem) {
        cartItemDto.setBookId(cartItem.getBook().getId());
    }

    @AfterMapping
    default void addBookToCartItem(CreateCartItemRequestDto requestDto, @MappingTarget CartItem cartItem) {
        if (requestDto.getBookId() != null) {
            Book book = new Book();
            book.setId(requestDto.getBookId());
            cartItem.setBook(book);
        }
    }
}
