package com.dev.cinema.model.dto.movie;

import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoMapper {
    private final MovieService movieService;
    @Autowired
    public MovieDtoMapper(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieRequestDto mapToMovie (MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movieService.add(movie);
        return movieRequestDto;
    }

    public MovieResponseDto mapMovieResponseDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setMovieId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }
}
