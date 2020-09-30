package com.dev.cinema.dao;

import com.dev.cinema.library.Dao;
import com.dev.cinema.model.Movie;
import java.util.Collections;
import java.util.List;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        return movie;
    }

    @Override
    public List<Movie> getAll() {
        return Collections.emptyList();
    }
}
