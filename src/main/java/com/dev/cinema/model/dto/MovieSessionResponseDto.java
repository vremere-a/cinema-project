package com.dev.cinema.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MovieSessionResponseDto {
    private Long MovieSessionId;
    private String ShowTime;
    private String movieTitle;
    private Long CinemaHallId;
}
