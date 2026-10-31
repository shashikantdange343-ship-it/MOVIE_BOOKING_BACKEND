package com.shashi.bookmovie.Services.Show;

import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Show.Seats;
import com.shashi.bookmovie.Entity.Status;
import com.shashi.bookmovie.Exceptons.CustomException;
import com.shashi.bookmovie.Services.Book.BookingService;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatUnlockScheduler {

    private final BookingService bookingService;

    // fixedRate = 60000 = 1 minute
    @Scheduled(fixedRate = 60000 * 2)
    @Transactional
    public void unlockExpiredSeats() {

        //for testing set 1 minute
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);

        // LIST OF EXPIRED BOOKINGS
        List<Booking> expiredBookings = bookingService.getAllExpiredBookings(Status.PENDING , tenMinutesAgo);

        if (!expiredBookings.isEmpty()) {
            System.out.println("⚠️ " + expiredBookings.size() + " expired bookings mili. Seats free kar raha hu...");

            for (Booking booking : expiredBookings) {
                booking.setStatus(Status.FAILED);

                try {
                    String intentId = booking.getPayment().getStripePaymentIntentId();
                    //INTENT.getClintSecrete = INTENT_ID    ---  REVERSE  --- INTENT.RETRIEVE(INTENT_ID) = INTENT
                    PaymentIntent intent = PaymentIntent.retrieve(intentId);

                    if ("requires_payment_method".equals(intent.getStatus()) || "requires_confirmation".equals(intent.getStatus())) {
                        intent.cancel();
                        booking.getPayment().setPaymentStatus(Status.FAILED);
                        System.out.println("✅ Stripe Intent Successfully Cancelled: " + intentId);
                    }
                } catch (Exception e) {
                    System.out.println("Stripe cancellation failed: " + e.getMessage());
                }
                List<Seats> seatsList = booking.getSeats();

                System.out.println("🔍 CHECKING: Booking ID " + booking.getId() + " ke paas " + seatsList.size() + " seats hain.");
                for (Seats seat : seatsList) {
                    seat.setIsAvailable(true);
                    seat.setBooking(null);
                }
            }

            System.out.println("✅ Saari expired seats wapas available ho gayi hain!");
        }
    }
}