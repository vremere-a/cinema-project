package com.dev.cinema.model.dto.hall;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CinemaHallRequestDto {
    @NotNull
    private int capacity;
    private String description;
}
