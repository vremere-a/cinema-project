package com.dev.cinema.model.dto.cart;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartDtoMapper {
    public ShoppingCartResponseDto mapToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto cartResponseDto = new ShoppingCartResponseDto();
        cartResponseDto.setCartId(shoppingCart.getId());
        List<Long> ticketsId = shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        cartResponseDto.setTicketsIds(ticketsId);
        return cartResponseDto;
    }
}
