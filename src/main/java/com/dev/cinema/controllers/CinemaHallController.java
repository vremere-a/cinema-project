package com.dev.cinema.controllers;

import com.dev.cinema.model.CinemaHall;
import java.util.List;
import java.util.stream.Collectors;

import com.dev.cinema.model.dto.cinemaHall.CinemaHallDtoMapper;
import com.dev.cinema.model.dto.cinemaHall.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallDtoMapper cinemaHallDtoMapper;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallDtoMapper cinemaHallDtoMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallDtoMapper = cinemaHallDtoMapper;
    }

    @GetMapping
    @ResponseBody
    public List<CinemaHallResponseDto> getAlCinemaHalls() {
        return cinemaHallService.getAll()
                .stream()
                .map(cinemaHallDtoMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }
}
