package com.dev.cinema.model.dto.moviesession;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MovieSessionDtoMapper {

    private final MovieSessionService movieSessionService;
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    @Autowired
    public MovieSessionDtoMapper(MovieSessionService movieSessionService,
                                 CinemaHallService cinemaHallService,
                                 MovieService movieService) {
        this.movieSessionService = movieSessionService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }
    public MovieSessionResponseDto mapMovieSessionResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieTitleId(movieSession.getMovie().getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(
                movieSession.getShowTime().format(DateTimeFormatter.ISO_LOCAL_DATE));
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        return movieSessionResponseDto;
    }

    public MovieSession mapToMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
//        movieSession.setId(movieSessionRequestDto.getId());
//        movieSession.setShowTime(LocalDateTime.parse(LocalDateTime.parse(movieSessionRequestDto.getShowTime()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        movieSession.setCinemaHall(cinemaHallService.getById(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setMovie(movieService.getById(movieSessionRequestDto.getMovieId()));
        movieSessionService.add(movieSession);
        return movieSession;
    }
}
