package com.shashi.bookmovie.DTOs.ShowDTOs;

import java.util.List;

public class TheatreDTOs {

    //LIST OF THEATRES THAT SHOWING ONE MOVIE
    public record TheatreDTO(

            Long theatreId,

            String theatreName,

            String theatreImage,

            String theatreLocation

    ){}

    public record TheatreDetailsDTO(
            List<ShowTimeDTOs.ShowTimesDTO> showTimes
    ){}

}
