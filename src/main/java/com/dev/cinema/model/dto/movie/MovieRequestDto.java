package com.dev.cinema.model.dto.movie;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MovieRequestDto {
    @NotNull
    @Size(min = 4)
    private String title;
    private String description;
}
