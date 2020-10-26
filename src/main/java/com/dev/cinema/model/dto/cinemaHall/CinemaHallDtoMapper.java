package com.dev.cinema.model.dto.cinemaHall;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.movie.MovieRequestDto;
import com.dev.cinema.model.dto.movie.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallDtoMapper {
//    public Movie mapToCinemaHall(MovieRequestDto movieRequestDto) {
//        CinemaHall cinemaHall = new CinemaHall();
//        cinemaHall.setTitle(movieRequestDto.getTitle());
//        return cinemaHall;

    public CinemaHallResponseDto mapToResponseDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }
}
//    }
//}
