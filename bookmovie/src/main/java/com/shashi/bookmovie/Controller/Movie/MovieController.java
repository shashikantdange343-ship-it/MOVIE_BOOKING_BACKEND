package com.shashi.bookmovie.Controller.Movie;

import com.shashi.bookmovie.DTOs.MovieDTOs.MovieDTOs;
import com.shashi.bookmovie.Services.Movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<Page<MovieDTOs.MovieDTO>> getAllMovies(@RequestParam int p){
        return ResponseEntity.ok(movieService.handleGetAllMovieRequest(p));
    }

    @GetMapping
    public ResponseEntity<MovieDTOs.OneMoviePageDTO> getMoviePage(@RequestParam Long movieId){
        return ResponseEntity.ok(movieService.handleOneMoviePageRequest(movieId));
    }

}
