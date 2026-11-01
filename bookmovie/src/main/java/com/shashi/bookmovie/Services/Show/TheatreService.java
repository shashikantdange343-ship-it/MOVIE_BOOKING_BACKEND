package com.shashi.bookmovie.Services.Show;

import com.shashi.bookmovie.DTOs.ShowDTOs.TheatreDTOs;
import com.shashi.bookmovie.Entity.Show.Theatre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TheatreService {

    Theatre getTheatre(Long theatreId);

    Page<Theatre> getAllTheatres(int p);

    List<Theatre> getOneMovieTheatres(Long movieId);

    List<TheatreDTOs.TheatreDTO> handleGetTheatresOfMovie(Long movieId);

    Page<TheatreDTOs.TheatreDTO> handleGetAllTheatres(int p);

    Page<TheatreDTOs.TheatreDTO> handleSearchTheatre(int p, String theatreName);

    TheatreDTOs.TheatreDetailsDTO handleGetTheatreDetails(Long theatreId);

}
