package com.shashi.bookmovie.Repository.Book;

import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Status;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @EntityGraph(attributePaths = {"seats"})
    Optional<List<Booking>> findByShowTimeId(Long showTimeId);

    List<Booking> findByStatusAndCreatedAtBefore(Status status, LocalDateTime time);

}
