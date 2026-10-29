package com.shashi.bookmovie.Services.Book;

import com.shashi.bookmovie.DTOs.BookingDTOs.PaymentDTOs;
import com.shashi.bookmovie.Entity.Book.Payment;
import org.springframework.stereotype.Component;

@Component
public class BookClassConverter {

    public PaymentDTOs.PaymentResponseDTO paymentResponseDTO(Payment payment){
        return new PaymentDTOs.PaymentResponseDTO(
                payment.getId(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getBooking().getStatus(),
                payment.getCreatedAt()
        );
    }
}
