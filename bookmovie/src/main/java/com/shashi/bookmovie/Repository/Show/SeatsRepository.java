package com.shashi.bookmovie.Repository.Show;

import com.shashi.bookmovie.Entity.Show.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatsRepository extends JpaRepository<Seats , Long > {

    List<Seats> findByShowTimeId(Long showTimeId);

}
