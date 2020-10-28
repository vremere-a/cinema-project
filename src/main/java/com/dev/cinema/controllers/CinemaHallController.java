package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.hall.CinemaHallDtoMapper;
import com.dev.cinema.model.dto.hall.CinemaHallRequestDto;
import com.dev.cinema.model.dto.hall.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(cinemaHallDtoMapper.mapToCinemaHall(cinemaHallRequestDto));
    }
}
