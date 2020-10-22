package com.dev.cinema.controllers;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieService movieService;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService, MovieService movieService) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
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

    @PostMapping
    public void add(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
//        movieSession.setId(movieSessionRequestDto.getMovieSessionId());
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        movieSession.setMovie(movieService.getById(movieSessionRequestDto.getMovieId()));
//        movieSession.setMovie(movieSessionRequestDto.getMovieTitle());
//        movieSession.setCinemaHall(movieSessionRequestDto.getCinemaHall());
        movieSessionService.add(movieSession);
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
