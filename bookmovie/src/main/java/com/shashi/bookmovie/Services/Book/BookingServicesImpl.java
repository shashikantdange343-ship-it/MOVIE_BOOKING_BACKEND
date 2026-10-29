package com.shashi.bookmovie.Services.Book;

import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Book.Payment;
import com.shashi.bookmovie.Entity.Show.ShowTime;
import com.shashi.bookmovie.Entity.Status;
import com.shashi.bookmovie.Entity.User.User;
import com.shashi.bookmovie.Exceptons.CustomException;
import com.shashi.bookmovie.Repository.Book.BookingRepository;
import com.shashi.bookmovie.Services.Show.SeatsService;
import com.shashi.bookmovie.Services.Show.ShowTimeService;
import com.shashi.bookmovie.Services.User.UserService;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServicesImpl implements BookingService{

    private final BookingRepository bookingRepository;

    private final SeatsService seatsService;

    private final ShowTimeService showTimeService;

    private final UserService userService;

    private final PaymentService paymentService;

    @Override
    @Transactional
    public String handleSeatBookingRequest(SeatDTOs.BookSeatsDTO seats , String userName) {


            Booking newBooking = new Booking();

            BigDecimal seatsTotalAmount = seatsService.handleSeatBookRequest(seats);

            ShowTime showTime = showTimeService.getShowTime(seats.showTimeId());

            User user = userService.getUser(userName);

        PaymentIntent intent = null;
            try {
             intent = paymentService.createPaymentIntent(seatsTotalAmount);
            } catch (Exception e) {
                throw new CustomException("Error creating Payment Intent !" + e.getMessage());
            }

            newBooking.setMovie(showTime.getMovie());
            newBooking.setSeats(seatsService.getAllSeats(seats.seats()));
            newBooking.setShowTime(showTime);
            newBooking.setStatus(Status.PENDING);
            newBooking.setTotalAmount(seatsTotalAmount);
            newBooking.setUser(user);
            newBooking.setPayment(paymentService.createPayment(newBooking, intent));

            bookingRepository.save(newBooking);

            return intent.getClientSecret();

    }



    @Override
    public Optional<List<Booking>> getBookingOfShowTime(Long showTimeId) {
        return bookingRepository.findByShowTimeId(showTimeId);
    }
}
