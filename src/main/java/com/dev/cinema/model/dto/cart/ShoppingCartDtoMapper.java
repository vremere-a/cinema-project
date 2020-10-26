package com.dev.cinema.model.dto.cart;

import com.dev.cinema.model.ShoppingCart;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartDtoMapper {
    public ShoppingCartResponseDto mapToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto cartResponseDto = new ShoppingCartResponseDto();
        cartResponseDto.setCartId(shoppingCart.getUser().getId());
        return cartResponseDto;
    }

}
