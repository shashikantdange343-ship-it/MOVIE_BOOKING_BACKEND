package com.shashi.bookmovie.Repository.Movie;

import com.shashi.bookmovie.Entity.Movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @EntityGraph(attributePaths = {"movieDetails"})
    Optional<Movie> findById(Long movieId);

    Page<Movie> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Movie> findByTheatresId(Long theatreId, Pageable pageable);

    @Query("select m from Movie m " +
            "where Lower(m.movieName) like lower(concat('%', :movieName,'%'))"
    )
    Page<Movie> findMoviesByName(@Param("movieName") String movieName, Pageable pageable);

}
