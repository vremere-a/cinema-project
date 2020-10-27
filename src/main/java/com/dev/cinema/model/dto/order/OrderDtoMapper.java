package com.dev.cinema.model.dto.order;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class OrderDtoMapper {
    public OrderResponseDto mapToResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setTicketsId(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        orderResponseDto.setOrderDate(order.getOrderDate());
        return orderResponseDto;
    }
}
