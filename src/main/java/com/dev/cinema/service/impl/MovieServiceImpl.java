package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Transactional
    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Transactional
    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Transactional
    @Override
    public Movie get(Long id) {
        return movieDao.get(id);
    }
}
