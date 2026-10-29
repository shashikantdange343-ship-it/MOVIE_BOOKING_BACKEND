package com.shashi.bookmovie.Services.Book;

import com.shashi.bookmovie.DTOs.BookingDTOs.PaymentDTOs;
import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Book.Payment;
import com.stripe.model.PaymentIntent;

import java.math.BigDecimal;

public interface PaymentService {

    void init();

    PaymentIntent createPaymentIntent(BigDecimal amount) throws Exception;

    Payment createPayment(Booking booking , PaymentIntent intent );

    PaymentDTOs.PaymentResponseDTO handlePayments(String payload , String sigHeader);

    Payment getPayment(String paymentIntentId);

    PaymentDTOs.PaymentResponseDTO handlePaymentFailure(PaymentIntent failedIntent);

    PaymentDTOs.PaymentResponseDTO handlePaymentSuccess(PaymentIntent paymentIntent);

}
