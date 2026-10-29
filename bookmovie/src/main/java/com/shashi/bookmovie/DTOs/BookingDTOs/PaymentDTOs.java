package com.shashi.bookmovie.DTOs.BookingDTOs;

import com.shashi.bookmovie.Entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTOs {

    public record PaymentResponseDTO(

            Long paymentId,

            String method,

            Status status,

            BigDecimal amount,

            String currency,

            Status bookingStatus,

            LocalDateTime time

    ){}

}
