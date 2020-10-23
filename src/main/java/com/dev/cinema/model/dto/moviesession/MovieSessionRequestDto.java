package com.dev.cinema.model.dto.moviesession;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MovieSessionRequestDto {
    private String showTime;
    private Long cinemaHallId;
    private Long movieId;
}
