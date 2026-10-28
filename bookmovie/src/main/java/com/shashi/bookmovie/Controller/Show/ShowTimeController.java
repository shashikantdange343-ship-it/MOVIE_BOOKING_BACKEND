package com.shashi.bookmovie.Controller.Show;

import com.shashi.bookmovie.DTOs.ShowDTOs.ShowTimeDTOs;
import com.shashi.bookmovie.Services.Show.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/movie/showtimes")
public class ShowTimeController {

    private final ShowTimeService showTimeService;


    @GetMapping("/all")
    public ResponseEntity<List<ShowTimeDTOs.ShowTimesDTO>> getShowTimes(@RequestParam Long movieId){
        return ResponseEntity.ok(showTimeService.handleGetTimesRequest(movieId));
    }


}
