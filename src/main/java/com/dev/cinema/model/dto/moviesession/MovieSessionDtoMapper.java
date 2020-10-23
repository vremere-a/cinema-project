package com.dev.cinema.model.dto.moviesession;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class MovieSessionDtoMapper {
    private final MovieService movieService;

    private CinemaHallService cinemaHallService;

    public MovieSessionDtoMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }
    public MovieSessionResponseDto mapMovieSessionResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieTitleId(movieSession.getMovie().getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(
                movieSession.getShowTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        return movieSessionResponseDto;
    }
}
