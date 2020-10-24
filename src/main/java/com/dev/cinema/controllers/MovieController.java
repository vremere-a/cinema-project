package com.dev.cinema.controllers;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.movie.MovieDtoMapper;
import com.dev.cinema.model.dto.movie.MovieRequestDto;
import com.dev.cinema.model.dto.movie.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class MovieController {
    private final MovieService movieService;
    private final MovieDtoMapper movieDtoMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieDtoMapper movieDtoMapper) {
        this.movieService = movieService;
        this.movieDtoMapper = movieDtoMapper;
    }

    @PostMapping("/movies")
    public void add(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieDtoMapper.mapToMovie(movieRequestDto));
//        movieDtoMapper.mapToMovie(movieRequestDto);
    }

    @GetMapping("/movies")
    @ResponseBody
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll()
                .stream()
                .map(movieDtoMapper::mapMovieResponseDto)
                .collect(Collectors.toList());
    }
}
