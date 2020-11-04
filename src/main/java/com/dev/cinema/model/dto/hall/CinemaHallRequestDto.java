package com.dev.cinema.model.dto.hall;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @NotNull(message = "capacity shouldn't be null")
    private Integer capacity;
    private String description;
}
