package com.shashi.bookmovie.Repository.Show;

import com.shashi.bookmovie.Entity.Show.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowTimeRepository extends JpaRepository<ShowTime , Long> {

    Optional<List<ShowTime>> findByMovieId(Long movieId);

}
