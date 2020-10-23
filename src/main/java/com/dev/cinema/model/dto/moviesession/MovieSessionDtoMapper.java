package com.dev.cinema.model.dto.moviesession;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionDtoMapper {
    private final MovieService movieService;

    private CinemaHallService cinemaHallService;

    public MovieSessionDtoMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }
    public MovieSessionResponseDto mapToDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
            movieSessionResponseDto.setMovieTitle(String.valueOf(movieSession.getMovie()));
            movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
            movieSessionResponseDto.setShowTime(String.valueOf(movieSession.getShowTime()));
            movieSessionResponseDto.setMovieSessionId(movieSession.getId());
            return movieSessionResponseDto;

    }
}
