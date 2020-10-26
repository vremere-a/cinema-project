package com.dev.cinema.model.dto.moviesession;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MovieSessionResponseDto {
    private Long movieSessionId;
    private String showTime;
    private Long movieId;
    private Long cinemaHallId;
}
