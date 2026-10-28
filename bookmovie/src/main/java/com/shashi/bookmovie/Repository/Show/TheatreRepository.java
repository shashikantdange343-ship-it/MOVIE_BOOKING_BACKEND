package com.shashi.bookmovie.Repository.Show;

import com.shashi.bookmovie.Entity.Show.Theatre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    @EntityGraph(attributePaths = {"showTime"})
    Optional<List<Theatre>> findByMoviesId(Long movieId);

}
