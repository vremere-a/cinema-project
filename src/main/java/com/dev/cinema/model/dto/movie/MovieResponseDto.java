package com.dev.cinema.model.dto.movie;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MovieResponseDto {
    private Long movieId;
    private String title;
    private String description;
}
