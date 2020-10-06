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

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie spiderMan = new Movie();
        spiderMan.setTitle("Spider Man");
        movieService.add(spiderMan);

        Movie ironMan = new Movie();
        ironMan.setTitle("Iron Man");
        movieService.add(ironMan);

        Movie flash = new Movie();
        flash.setTitle("Flash");
        movieService.add(flash);

        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall marvelHall = new CinemaHall();
        marvelHall.setCapacity(100);
        cinemaHallService.add(marvelHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession spiderManSession = new MovieSession();
        spiderManSession.setCinemaHall(marvelHall);
        spiderManSession.setMovie(spiderMan);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        spiderManSession.setShowTime(LocalDateTime.now().plusMonths(5));
        movieSessionService.add(spiderManSession);

        MovieSession ironManSession = new MovieSession();
        ironManSession.setCinemaHall(marvelHall);
        ironManSession.setMovie(ironMan);
        ironManSession.setShowTime(LocalDateTime.now().plusMonths(3));
        movieSessionService.add(ironManSession);

        MovieSession flashSession = new MovieSession();
        flashSession.setCinemaHall(marvelHall);
        flashSession.setMovie(flash);
        flashSession.setShowTime(LocalDateTime.now().plusMonths(2));
        movieSessionService.add(flashSession);

        movieSessionService.findAvailableSessions(spiderMan.getId(),LocalDate.now())
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(ironMan.getId(),LocalDate.now())
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(flash.getId(),LocalDate.now())
                .forEach(System.out::println);
    }
}
