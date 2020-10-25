package com.dev.cinema.model.dto.moviesession;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MovieSessionRequestDto {
    private LocalDateTime showTime;
    private Long cinemaHallId;
    private Long movieId;
}
