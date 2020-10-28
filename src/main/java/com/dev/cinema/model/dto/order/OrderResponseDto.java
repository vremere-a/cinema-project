package com.dev.cinema.model.dto.order;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private List<Long> ticketsId;
    private LocalDateTime orderDate;
    private Long userId;
}
