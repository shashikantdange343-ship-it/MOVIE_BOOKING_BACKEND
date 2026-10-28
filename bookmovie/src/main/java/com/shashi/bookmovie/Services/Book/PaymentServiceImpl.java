package com.shashi.bookmovie.Services.Book;

import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Book.Payment;
import com.shashi.bookmovie.Entity.Status;
import com.shashi.bookmovie.Exceptons.CustomException;
import com.shashi.bookmovie.Repository.Book.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Value("${stripe.api.key}")
    private String stripeKey;

    @Override
    public void init() {
        Stripe.apiKey = stripeKey;
    }

    @Override
    public PaymentIntent createPaymentIntent(BigDecimal amount) throws Exception {

        long amountInPaise = amount.multiply(new BigDecimal("100")).longValue();

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amountInPaise)
                        .setCurrency("INR")
                        .build();

        PaymentIntent intent = PaymentIntent.create(params);

       return intent;
    }

    @Override
    public Payment createPayment(Booking booking , PaymentIntent intent){
        Payment payment = new Payment();
        payment.setAmount(booking.getTotalAmount());
        payment.setCurrency("INR");
        payment.setPaymentStatus(Status.PENDING);
        payment.setPaymentMethod(null);
        payment.setBooking(booking);
        payment.setStripePaymentIntentId(intent.getId());

        return payment;
    }

}
