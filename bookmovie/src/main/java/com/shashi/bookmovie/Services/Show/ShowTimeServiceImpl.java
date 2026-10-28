package com.shashi.bookmovie.Services.Show;

import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDetailsDTO;
import com.shashi.bookmovie.DTOs.ShowDTOs.ShowTimeDTOs;
import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Show.Seats;
import com.shashi.bookmovie.Entity.Show.ShowTime;
import com.shashi.bookmovie.Exceptons.ResourceNotFoundException;
import com.shashi.bookmovie.Repository.Book.BookingRepository;
import com.shashi.bookmovie.Repository.Show.ShowTimeRepository;
import com.shashi.bookmovie.Services.Book.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowClassConverter showClassConverter;

    private final ShowTimeRepository showTimeRepository;

    @Override
    public ShowTime getShowTime(Long showTimeId) {

        Optional<ShowTime> optionalShowTime = showTimeRepository.findById(showTimeId);
        if (optionalShowTime.isEmpty()){
            throw new ResourceNotFoundException("No Show Time found !");
        }
        return optionalShowTime.get();
    }

    @Override//GETTING LIST OF SHOWTIME IF A MOVIE IN THEATRE
    public List<ShowTime> getShowTimes(Long movieId) {
        Optional<List<ShowTime>> optionalShowTimes = showTimeRepository.findByMovieId(movieId);
        if (optionalShowTimes.isEmpty()){
            throw new ResourceNotFoundException("No Show Time Found !");
        }
        return optionalShowTimes.get();
    }

    @Override//RETURNING A SPECIFIC MOVIE SHOWTIME IN THEATRE
    public List<ShowTimeDTOs.ShowTimesDTO> handleGetTimesRequest(Long movieId) {

       return getShowTimes(movieId).stream().map(showClassConverter::showTimesDTO).toList();

    }

}
