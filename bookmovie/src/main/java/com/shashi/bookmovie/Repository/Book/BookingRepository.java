package com.shashi.bookmovie.Repository.Book;

import com.shashi.bookmovie.Entity.Book.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<List<Booking>> findByShowTimeId(Long showTimeId);

}
