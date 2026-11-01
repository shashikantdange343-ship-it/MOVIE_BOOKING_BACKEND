package com.shashi.bookmovie.Repository.Show;

import com.shashi.bookmovie.Entity.Show.Theatre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    @EntityGraph(attributePaths = {"showTime"})
    Optional<List<Theatre>> findByMoviesId(Long movieId);

    @Query("select t from Theatre t " +
            "where Lower(t.theatreName) like lower(concat('%', :theatreName,'%'))"
//            "or Lower(p.categories.name) like Lower(concat('%', :search , '%'))"+
    )
    Page<Theatre> findTheatresByName(@Param("theatreName") String theatreName , Pageable pageable);

}
