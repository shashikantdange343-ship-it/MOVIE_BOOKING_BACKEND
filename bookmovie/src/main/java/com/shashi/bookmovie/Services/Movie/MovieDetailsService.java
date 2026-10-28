package com.shashi.bookmovie.Services.Movie;

import com.shashi.bookmovie.DTOs.MovieDTOs.MovieDetailsDTOs;
import com.shashi.bookmovie.Entity.Movie.MovieDetails;

public interface MovieDetailsService {

    MovieDetails getMovieDetails(Long movieId);

    MovieDetailsDTOs.MovieDetailsDTO handleGetDetailsRequest(Long movieId);

}
