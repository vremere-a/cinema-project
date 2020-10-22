package com.dev.cinema.controllers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie-session")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/available")
    @ResponseBody
    public List<MovieSessionResponseDto> getAllAvailableMovieSession(@RequestParam("movieId") Long movieId,
                                                                     @RequestParam("date") LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date)
                .stream()
                .map(MovieSessionController.this::mapMovieSessionResponseDto)
                .collect(Collectors.toList());
    }

    private MovieSessionResponseDto mapMovieSessionResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieTitle(String.valueOf(movieSession.getMovie()));
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(String.valueOf(movieSession.getShowTime()));
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        return movieSessionResponseDto;
    }

}
