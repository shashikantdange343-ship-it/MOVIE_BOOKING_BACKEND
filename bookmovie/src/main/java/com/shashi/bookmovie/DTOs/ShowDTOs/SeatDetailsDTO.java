package com.shashi.bookmovie.DTOs.ShowDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//LIST OF SEATS OF A SHOWTIME IN THEATRE
//NOT A RECORD CAUSE "IS AVAILABLE" CHANGING DYNAMICALLY
public class SeatDetailsDTO {

    private Long seatId;

    private String row;

    private Integer column;

    private boolean isAvailable;
}
