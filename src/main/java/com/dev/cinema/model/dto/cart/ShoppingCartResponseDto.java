package com.dev.cinema.model.dto.cart;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingCartResponseDto {
    private Long cartId;
    private List<Long> ticketsIds;
}
