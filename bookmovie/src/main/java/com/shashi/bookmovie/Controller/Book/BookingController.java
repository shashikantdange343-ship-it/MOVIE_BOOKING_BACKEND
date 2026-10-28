package com.shashi.bookmovie.Controller.Book;

import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.Services.Book.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/book")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<String> handleSeatBooking(@RequestBody SeatDTOs.BookSeatsDTO bookSeatsDTO , Principal principal){
        return ResponseEntity.ok(bookingService.handleSeatBookingRequest(bookSeatsDTO, principal.getName()));
    }

}
