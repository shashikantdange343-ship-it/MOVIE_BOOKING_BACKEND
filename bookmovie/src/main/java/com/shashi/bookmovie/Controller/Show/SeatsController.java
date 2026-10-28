package com.shashi.bookmovie.Controller.Show;

import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.Services.Show.SeatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/Seats")
@CrossOrigin("*")
public class SeatsController {

    private final SeatsService seatsService;

    @GetMapping
    public ResponseEntity<SeatDTOs.SeatDTO> getSeats(@RequestParam Long showTimeId){
        return ResponseEntity.ok(seatsService.handleShowTimeSeats(showTimeId));
    }

}
