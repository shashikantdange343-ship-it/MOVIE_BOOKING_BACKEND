package com.shashi.bookmovie.DTOs.ShowDTOs;

import java.util.List;

public class TheatreDTOs {

    //LIST OF THEATRES THAT SHOWING ONE MOVIE
    public record TheatreDTO(

            Long theatreId,

            String theatreName,

            List<ShowTimeDTOs.ShowTimesDTO> showTimes

    ){}

}
