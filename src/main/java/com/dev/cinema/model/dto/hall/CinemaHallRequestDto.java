package com.dev.cinema.model.dto.hall;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @NotNull
    private int capacity;
    private String description;
}
