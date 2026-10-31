package com.shashi.bookmovie.Controller.Book;

import com.shashi.bookmovie.DTOs.BookingDTOs.PaymentDTOs;
import com.shashi.bookmovie.Services.Book.PaymentService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/webhook")
    public ResponseEntity<PaymentDTOs.PaymentResponseDTO> handleStripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {

        return ResponseEntity.ok(paymentService.handlePayments(payload, sigHeader));
    }
}
