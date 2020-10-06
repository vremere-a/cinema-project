package com.dev.cinema;

import com.dev.cinema.library.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        movieService.getAll().forEach(System.out::println);

        //add new movie
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
        CinemaHallService cinemaHallService = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        //add new cinemaHall
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall = cinemaHallService.add(cinemaHall);
        //add new MovieSession
        MovieSessionService movieSessionService = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(19,30)));
        movieSessionService.add(movieSession);

        movieSessionService.findAvailableSessions(movie.getId(),LocalDate.now())
                .forEach(System.out::println);

    }
}
