package com.dev.cinema.service.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.library.Inject;
import com.dev.cinema.library.Service;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return null;
    }

    @Override
    public List<CinemaHall> getAll() {
        return null;
    }
}
