package com.shashi.bookmovie.Services.Show;

import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDetailsDTO;
import com.shashi.bookmovie.DTOs.ShowDTOs.ShowTimeDTOs;
import com.shashi.bookmovie.DTOs.ShowDTOs.TheatreDTOs;
import com.shashi.bookmovie.Entity.Show.Seats;
import com.shashi.bookmovie.Entity.Show.ShowTime;
import com.shashi.bookmovie.Entity.Show.Theatre;
import org.springframework.stereotype.Component;

@Component
public class ShowClassConverter {

    public ShowTimeDTOs.ShowTimesDTO showTimesDTO(ShowTime showTime){
        return new ShowTimeDTOs.ShowTimesDTO(
                showTime.getId(),
                showTime.getShowTime(),
                showTime.getMovie().getMovieName(),
                showTime.getAvailableSeats()
        );
    }

    public TheatreDTOs.TheatreDTO theatreDTO(Theatre theatre){
        return new TheatreDTOs.TheatreDTO(
                theatre.getId(),
                theatre.getTheatreName(),
                theatre.getShowTime().stream().map(this::showTimesDTO).toList()
        );
    }

    public SeatDTOs.SeatDetailsDTO seatDetailsDTO(Seats seats){
        return new SeatDTOs.SeatDetailsDTO(
                seats.getId(),
                seats.getSeatRow(),
                seats.getSeatColumn(),
                seats.getIsAvailable()
        );
    }

    public SeatDTOs.SeatDTO seatDTO(ShowTime showTime){
        return new SeatDTOs.SeatDTO(
                showTime.getTotalSeats(),
                showTime.getSeats().stream().map(this::seatDetailsDTO).toList()
        );
    }


}
