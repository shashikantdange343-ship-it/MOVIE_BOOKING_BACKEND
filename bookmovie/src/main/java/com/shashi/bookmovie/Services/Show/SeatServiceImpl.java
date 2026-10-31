package com.shashi.bookmovie.Services.Show;

import com.shashi.bookmovie.DTOs.ResponseMessageDTO;
import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDetailsDTO;
import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Show.Seats;
import com.shashi.bookmovie.Entity.Show.ShowTime;
import com.shashi.bookmovie.Entity.Status;
import com.shashi.bookmovie.Exceptons.CustomException;
import com.shashi.bookmovie.Exceptons.ResourceNotFoundException;
import com.shashi.bookmovie.Repository.Show.SeatsRepository;
import com.shashi.bookmovie.Services.Book.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatsService {

    private final SeatsRepository seatsRepository;

    private final ShowTimeService showTimeService;

    private final ShowClassConverter showClassConverter;


    @Override
    public Seats saveSeats(Seats seats) {
        return seatsRepository.save(seats);
    }

    @Override
    public Seats getSeats(Long seatId) {
       Optional<Seats> seats = seatsRepository.findById(seatId);
       if (seats.isEmpty()){
           throw new ResourceNotFoundException("No Seat Found By Id : "+ seatId);
       }
        return seats.get();
    }

    @Override
    public List<Seats> getAllSeats(Long showTimeId) {

        List<Seats> seats = seatsRepository.findByShowTimeId(showTimeId);
        if (seats.isEmpty()){
            throw new ResourceNotFoundException("No Seats Found ! ");
        }

        return seats;
    }

    @Override
    public List<Seats> getAllSeats(List<SeatDTOs.SeatBookRequestDTO> seatsId) {
        List<Seats> seats = new ArrayList<>();

        for (SeatDTOs.SeatBookRequestDTO seat1 : seatsId){
            Optional<Seats> optionalSeats = seatsRepository.findById(seat1.seatId());
            if(optionalSeats.isEmpty()){
                throw new ResourceNotFoundException("Seat Not Found With Id : " + seat1.seatId());
            }
            seats.add(optionalSeats.get());
        }

        return seats;
    }

    @Override//A SPECIFIC MOVIE'S SPECIFIC THEATRE'S SPECIFIC SHOWTIME SEATS WITH BOOLEAN IS AVAILABLE
    public SeatDTOs.SeatDTO handleShowTimeSeats(Long showTimeId) {

        ShowTime showTime = showTimeService.getShowTime(showTimeId);
        return showClassConverter.seatDTO(showTime);

    }

    @Override
    public BigDecimal handleSeatBookRequest(SeatDTOs.BookSeatsDTO seats , Booking booking) {

        BigDecimal totalAmount = BigDecimal.valueOf(0);
           for (SeatDTOs.SeatBookRequestDTO seat1 : seats.seats()){
               Seats entitySeats = getSeats(seat1.seatId());
               if (entitySeats.getIsAvailable() == false){
                   throw new CustomException("Seat is Not Available ! " + seat1.seatId() + seat1.seatId() );
               }
               entitySeats.setIsAvailable(false);
               entitySeats.setBooking(booking);
               seatsRepository.save(entitySeats);
               totalAmount = totalAmount.add(entitySeats.getPrice());
           }

        return totalAmount;
    }

    @Override
    public void saveAllSeats(List<Seats> seats) {
        seatsRepository.saveAll(seats);
    }


}
