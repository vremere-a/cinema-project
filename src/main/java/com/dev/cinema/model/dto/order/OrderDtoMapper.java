package com.dev.cinema.model.dto.order;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapper {
    public OrderResponseDto mapToResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setTicketsIds(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        orderResponseDto.setOrderDate(order.getOrderDate()
                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        return orderResponseDto;
    }
}
