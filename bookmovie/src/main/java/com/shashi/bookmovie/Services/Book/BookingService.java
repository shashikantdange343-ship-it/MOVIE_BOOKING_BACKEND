package com.shashi.bookmovie.Services.Book;

import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Book.Payment;
import com.shashi.bookmovie.Entity.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    String handleSeatBookingRequest(SeatDTOs.BookSeatsDTO seats , String userName);

    Optional<List<Booking>> getBookingOfShowTime(Long showTimeId);

    List<Booking> saveAllBookings(List<Booking> bookings);

    List<Booking> getAllExpiredBookings(Status status , LocalDateTime expiredTime);

}
