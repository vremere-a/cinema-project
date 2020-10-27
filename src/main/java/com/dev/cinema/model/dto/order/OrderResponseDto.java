package com.dev.cinema.model.dto.order;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private List<Long> ticketsId;
    private LocalDateTime orderDate;
    private Long userId;
}
