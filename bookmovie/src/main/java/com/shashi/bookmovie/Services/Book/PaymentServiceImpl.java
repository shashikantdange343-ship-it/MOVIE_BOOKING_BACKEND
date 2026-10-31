package com.shashi.bookmovie.Services.Book;

import com.shashi.bookmovie.DTOs.BookingDTOs.PaymentDTOs;
import com.shashi.bookmovie.DTOs.ShowDTOs.SeatDTOs;
import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Book.Payment;
import com.shashi.bookmovie.Entity.Show.Seats;
import com.shashi.bookmovie.Entity.Status;
import com.shashi.bookmovie.Exceptons.CustomException;
import com.shashi.bookmovie.Exceptons.ResourceNotFoundException;
import com.shashi.bookmovie.Repository.Book.BookingRepository;
import com.shashi.bookmovie.Repository.Book.PaymentRepository;
import com.shashi.bookmovie.Services.Show.SeatsService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    private final SeatsService seatsService;

    private final BookingRepository bookingRepository;

    private final BookClassConverter bookClassConverter;

    @Value("${stripe.api.key}")
    private String stripeKey;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostConstruct
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

    @Override
    public PaymentDTOs.PaymentResponseDTO handlePayments(String payload, String sigHeader) {
        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            throw new CustomException("Invalid Signature !");
        } catch (Exception e) {
            throw new CustomException("Invalid Payload !");
        }

        if (event.getType().equals("payment_intent.succeeded")) {

            PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer().getObject().get();
            System.out.println("✅ Payment Succeeded for Intent ID: " + paymentIntent.getId());

            return handlePaymentSuccess(paymentIntent);
        }
        else if(event.getType().equals("payment_intent.payment_failed")){
            PaymentIntent failedIntent = (PaymentIntent) event.getDataObjectDeserializer().getObject().get();
            System.out.println("❌ Payment Failed for Intent ID: " + failedIntent.getId());

            return handlePaymentFailure(failedIntent);
        }
        else {
            System.out.println("Unhandled event type: " + event.getType());
            return null;
        }
    }

    @Override
    public Payment getPayment(String paymentIntentId) {
        Optional<Payment> optionalPayment = paymentRepository.findByStripePaymentIntentId(paymentIntentId);
        if (optionalPayment.isEmpty()){
            throw new ResourceNotFoundException("No Payment Intent Found !");
        }
        return optionalPayment.get();
    }

    @Override
    public PaymentDTOs.PaymentResponseDTO handlePaymentFailure(PaymentIntent failedIntent) {

        Payment payment = getPayment(failedIntent.getId());

        Booking booking = payment.getBooking();

        payment.setPaymentStatus(Status.FAILED);
        payment.setPaymentMethod(failedIntent.getPaymentMethod());
        booking.setStatus(Status.FAILED);

        for (Seats seat : booking.getSeats()) {
            seat.setIsAvailable(true);
        }

        seatsService.saveAllSeats(booking.getSeats());

        bookingRepository.save(booking);

        return bookClassConverter.paymentResponseDTO(paymentRepository.save(payment));

    }

    @Override
    public PaymentDTOs.PaymentResponseDTO handlePaymentSuccess(PaymentIntent paymentIntent) {

        Payment payment = getPayment(paymentIntent.getId());

        Booking booking = payment.getBooking();

        if (booking.getStatus() == Status.FAILED) {
            System.out.println("Late Payment aayi hai! Seats free ho chuki hain, Initiating Auto-Refund...");

            try {
                // User ko uske paise wapas bhej do
                RefundCreateParams params = RefundCreateParams.builder()
                        .setPaymentIntent(paymentIntent.getId())
                        .build();
                Refund.create(params);

                payment.setPaymentStatus(Status.REFUNDED);
                return bookClassConverter.paymentResponseDTO(paymentRepository.save(payment));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        payment.setPaymentStatus(Status.SUCCESS);
        payment.setPaymentMethod(paymentIntent.getPaymentMethod());
        booking.setStatus(Status.SUCCESS);

        for (Seats seat : booking.getSeats()) {
            seat.setIsAvailable(false);
        }

        seatsService.saveAllSeats(booking.getSeats());
        bookingRepository.save(booking);

        return bookClassConverter.paymentResponseDTO(paymentRepository.save(payment));
    }

}
