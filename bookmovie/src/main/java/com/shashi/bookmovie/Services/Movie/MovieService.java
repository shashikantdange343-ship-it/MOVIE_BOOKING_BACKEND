package com.shashi.bookmovie.Services.Movie;

import com.shashi.bookmovie.DTOs.MovieDTOs.MovieDTOs;
import com.shashi.bookmovie.Entity.Movie.Movie;
import org.springframework.data.domain.Page;

public interface MovieService {

    Movie getMovie(Long movieId);

    Page<MovieDTOs.MovieDTO> handleGetAllMovieRequest(int p);

    Page<Movie> getAllMovies(int p);

    MovieDTOs.OneMoviePageDTO handleOneMoviePageRequest(Long movieId);

}
