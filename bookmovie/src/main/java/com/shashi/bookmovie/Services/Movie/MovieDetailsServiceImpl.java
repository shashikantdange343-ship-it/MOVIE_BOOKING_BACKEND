package com.shashi.bookmovie.Services.Movie;

import com.shashi.bookmovie.DTOs.MovieDTOs.MovieDetailsDTOs;
import com.shashi.bookmovie.Entity.Movie.Movie;
import com.shashi.bookmovie.Entity.Movie.MovieDetails;
import com.shashi.bookmovie.Exceptons.ResourceNotFoundException;
import com.shashi.bookmovie.Repository.Movie.MovieDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieDetailsServiceImpl implements MovieDetailsService {

    private final MovieDetailsRepository movieDetailsRepository;

    private final MovieService movieService;

    private final MovieClassConverter movieClassConverter;

    @Override
    public MovieDetails getMovieDetails(Long movieId) {

       Optional<MovieDetails> optionalMovieDetails = movieDetailsRepository.findByMovieId(movieId);
        if (optionalMovieDetails.isEmpty()){
            throw new ResourceNotFoundException("Movie Details Not Found !");
        }
        return optionalMovieDetails.get();
    }


    @Override
    public MovieDetailsDTOs.MovieDetailsDTO handleGetDetailsRequest(Long movieId) {

        Movie movie = movieService.getMovie(movieId);
        MovieDetails movieDetails = getMovieDetails(movieId);

        return movieClassConverter.movieDetailsDTO(movieDetails,movie);
    }
}
