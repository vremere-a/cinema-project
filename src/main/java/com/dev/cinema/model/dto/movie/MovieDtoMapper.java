package com.dev.cinema.model.dto.movie;

import com.dev.cinema.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoMapper {


    public MovieResponseDto mapMovieResponseDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setMovieId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }
}
