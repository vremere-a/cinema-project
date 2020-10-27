package com.dev.cinema.model.dto.cart;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartResponseDto {
    private Long cartId;
    private List<Long> ticketsId;
}
