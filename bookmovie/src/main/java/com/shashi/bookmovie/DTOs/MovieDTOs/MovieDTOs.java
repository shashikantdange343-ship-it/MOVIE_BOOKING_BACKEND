package com.shashi.bookmovie.DTOs.MovieDTOs;

import com.shashi.bookmovie.DTOs.ShowDTOs.TheatreDTOs;

import java.time.LocalDateTime;
import java.util.List;

public class MovieDTOs {

    //GETTING LIST OF MOVIES
    public record MovieDTO(

            Long id,

            String movieName,

            String director,

            String primaryImageUrl,

            String description,

            LocalDateTime releasedDate

    ) {}

    //ONE MOVIE INFORMATION LIKE THEATRES , IN THEATRE SHOWTIMES
    public record OneMoviePageDTO(

            MovieDTO movieDTO,

            List<TheatreDTOs.TheatreDTO> theatres
    ){}

}
