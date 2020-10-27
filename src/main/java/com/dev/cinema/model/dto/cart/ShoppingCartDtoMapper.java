package com.dev.cinema.model.dto.cart;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class ShoppingCartDtoMapper {
    public ShoppingCartResponseDto mapToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto cartResponseDto = new ShoppingCartResponseDto();
        cartResponseDto.setCartId(shoppingCart.getUser().getId());
        List<Long> ticketsId = new ArrayList<>();
        for (Ticket ticket: shoppingCart.getTickets()) {
            ticketsId.add(ticket.getId());
        }
        cartResponseDto.setTicketsId(ticketsId);
        return cartResponseDto;
    }

}
