package com.shashi.bookmovie.Repository.Movie;

import com.shashi.bookmovie.Entity.Movie.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieDetailsRepository extends JpaRepository<MovieDetails,Long> {

    Optional<MovieDetails> findByMovieId(Long movieId);

}
