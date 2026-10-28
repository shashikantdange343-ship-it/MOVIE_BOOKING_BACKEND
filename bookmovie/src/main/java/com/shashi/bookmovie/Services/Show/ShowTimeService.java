package com.shashi.bookmovie.Services.Show;

import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.DTOs.ShowDTOs.ShowTimeDTOs;
import com.shashi.bookmovie.Entity.Show.ShowTime;

import java.util.List;

public interface ShowTimeService {

    ShowTime getShowTime(Long showTimeId);

    List<ShowTime> getShowTimes(Long movieId);

    List<ShowTimeDTOs.ShowTimesDTO> handleGetTimesRequest(Long movieId);

}
