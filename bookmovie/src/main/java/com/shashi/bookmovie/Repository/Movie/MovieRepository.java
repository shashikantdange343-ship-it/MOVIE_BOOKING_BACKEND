package com.shashi.bookmovie.Repository.Movie;

import com.shashi.bookmovie.Entity.Movie.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
