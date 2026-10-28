package com.shashi.bookmovie.Controller.Movie;

import com.shashi.bookmovie.DTOs.MovieDTOs.MovieDetailsDTOs;
import com.shashi.bookmovie.Services.Movie.MovieDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/movie/details")
public class MovieDetailsController {

    private final MovieDetailsService movieDetailsService;

    @GetMapping
    public ResponseEntity<MovieDetailsDTOs.MovieDetailsDTO> getMovieDetails(@RequestParam Long movieId){
        return ResponseEntity.ok(movieDetailsService.handleGetDetailsRequest(movieId));
    }

}
