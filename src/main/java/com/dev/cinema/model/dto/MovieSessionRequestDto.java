package com.dev.cinema.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MovieSessionRequestDto {
    private Long movieSessionId;
    private String showTime;
    private String movieTitle;
    private String cinemaHall;
    private Long movieId;
}
