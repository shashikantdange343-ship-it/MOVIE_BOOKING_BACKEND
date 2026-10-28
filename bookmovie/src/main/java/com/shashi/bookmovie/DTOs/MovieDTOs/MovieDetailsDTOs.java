package com.shashi.bookmovie.DTOs.MovieDTOs;

import java.util.List;

public class MovieDetailsDTOs {

    //MOVIE DETAILS
    public record MovieDetailsDTO(

            List<String> producerName,

            List<String> movieImageUrl,

            List<String> movieTrailerUrl,

            List<String> movieSongs,

            List<String> actors,

            List<String> actress

    ){}

}
