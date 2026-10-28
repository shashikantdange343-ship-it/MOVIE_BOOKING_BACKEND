package com.shashi.bookmovie.DTOs.ShowDTOs;

import java.time.LocalDateTime;
import java.util.List;

public class ShowTimeDTOs {

    //LIST OF SHOWTIME IN ONE THEATRE
    public record ShowTimesDTO(

            Long id ,

            LocalDateTime showTime,

            String movieName,

            Integer totalAvailableSeats

    ){}


}
