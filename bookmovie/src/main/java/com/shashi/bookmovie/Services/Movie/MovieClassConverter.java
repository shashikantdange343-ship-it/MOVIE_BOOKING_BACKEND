package com.shashi.bookmovie.Services.Movie;

import com.shashi.bookmovie.DTOs.MovieDTOs.MovieDTOs;
import com.shashi.bookmovie.DTOs.MovieDTOs.MovieDetailsDTOs;
import com.shashi.bookmovie.Entity.Movie.Movie;
import com.shashi.bookmovie.Entity.Movie.MovieDetails;
import org.springframework.stereotype.Component;

@Component
public class MovieClassConverter {

    public MovieDTOs.MovieDTO movieDTO(Movie movie){
        return new MovieDTOs.MovieDTO(
                movie.getId(),
                movie.getMovieName(),
                movie.getDirectorName(),
                movie.getMovieImageUrl(),
                movie.getMovieDescription(),
                movie.getReleasedAt()
        );
    }

    public MovieDetailsDTOs.MovieDetailsDTO movieDetailsDTO(MovieDetails movieDetails , Movie movie){
        return new MovieDetailsDTOs.MovieDetailsDTO(
                movieDetails.getProducerName(),
                movieDetails.getMovieImageUrl(),
                movieDetails.getMovieTrailerUrl(),
                movieDetails.getMovieSongs(),
                movieDetails.getActors(),
                movieDetails.getActress()
        );
    }

//    public MovieDTOs.MoviePageDTO moviePageDTO(Movie movie ){
//        return new MovieDTOs.MoviePageDTO(
//                movieDTO(movie),
//
//        )
//    }

}
