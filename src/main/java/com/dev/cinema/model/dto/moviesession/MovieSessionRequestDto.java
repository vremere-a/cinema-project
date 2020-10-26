package com.dev.cinema.model.dto.moviesession;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MovieSessionRequestDto {
    private LocalDateTime showTime;
    private Long cinemaHallId;
    private Long movieId;
}
