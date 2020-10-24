package com.dev.cinema.dao;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;

import java.util.List;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall getById(Long id);
}
