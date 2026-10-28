package com.shashi.bookmovie.Entity.Book;

import com.shashi.bookmovie.Entity.Movie.Movie;
import com.shashi.bookmovie.Entity.Show.Seats;
import com.shashi.bookmovie.Entity.Show.ShowTime;
import com.shashi.bookmovie.Entity.Show.Theatre;
import com.shashi.bookmovie.Entity.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String stripePaymentIntentId;

    // 2. PAISA (Hamesha BigDecimal use kar, Double nahi, warna calculation me error aata hai)
    @Column(nullable = false)
    private BigDecimal amount;

    // 3. CURRENCY (Stripe ko batana padta hai ki INR hai ya USD)
    private String currency = "INR";

    // 4. STATUS (Jaise tune socha tha: PENDING, SUCCESS, FAILED)
    @Column(nullable = false)
    private Status paymentStatus;

    // 5. PAYMENT METHOD (Optional: user ne Card se kiya ya UPI se, receipt ke liye acha hota hai)
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}