package com.shashi.bookmovie.Services.Show;

import com.shashi.bookmovie.DTOs.ResponseMessageDTO;
import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.Entity.Show.Seats;

import java.math.BigDecimal;
import java.util.List;

public interface SeatsService {

    Seats saveSeats(Seats seats);

    Seats getSeats(Long seatId);

    List<Seats> getAllSeats(Long showTimeId);

    List<Seats> getAllSeats(List<SeatDTOs.SeatBookRequestDTO> seatsId);

    SeatDTOs.SeatDTO handleShowTimeSeats(Long showTimeId);

    BigDecimal handleSeatBookRequest(SeatDTOs.BookSeatsDTO seats);

    void saveAllSeats(List<Seats> seats);

}

