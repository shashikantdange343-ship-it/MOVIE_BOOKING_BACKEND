package com.shashi.bookmovie.DTOs.ShowDTOs;

import java.util.List;

public class SeatDTOs {

    public record SeatDetailsDTO(
            Long seatId,

            String row,

            Integer column,

            boolean isAvailable
    ){}

    //WRAPPER WITH TOTAL SEATS
    public record SeatDTO(

            Integer totalSeats,

            List<SeatDetailsDTO> seats
    ){}

    public record SeatBookRequestDTO(
            Long seatId
    ){}

    public record BookSeatsDTO(

            Long showTimeId,

            List<SeatBookRequestDTO> seats
    ){}
}
