package com.dev.cinema.model.dto.movie;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MovieRequestDto {
    private String title;
}
