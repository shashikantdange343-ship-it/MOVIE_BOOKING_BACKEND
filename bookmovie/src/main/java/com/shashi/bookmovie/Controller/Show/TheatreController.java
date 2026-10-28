package com.shashi.bookmovie.Controller.Show;

import com.shashi.bookmovie.DTOs.ShowDTOs.TheatreDTOs;
import com.shashi.bookmovie.Services.Show.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/theatres")
@CrossOrigin("*")
public class TheatreController {

    private final TheatreService theatreService;

    @GetMapping("/all")
    public ResponseEntity<Page<TheatreDTOs.TheatreDTO>> getAllTheatres(@RequestParam int p){
        return ResponseEntity.ok(theatreService.handleGetAllTheatres(p));
    }

    @GetMapping
    public ResponseEntity<List<TheatreDTOs.TheatreDTO>> getTheatresOfMovie(@RequestParam Long movieId){
       return ResponseEntity.ok(theatreService.handleGetTheatresOfMovie(movieId));
    }


}
