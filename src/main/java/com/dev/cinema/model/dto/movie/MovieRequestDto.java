package com.dev.cinema.model.dto.movie;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequestDto {
    @NotNull(message = "Please provide title for movie")
    @Size(min = 4)
    private String title;
    @Size(max = 40, message = "description should not exceed 40 characters")
    private String description;
}
