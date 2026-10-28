package com.shashi.bookmovie.Entity.Book;

import com.shashi.bookmovie.Entity.Movie.Movie;
import com.shashi.bookmovie.Entity.Show.Seats;
import com.shashi.bookmovie.Entity.Show.ShowTime;
import com.shashi.bookmovie.Entity.Show.Theatre;
import com.shashi.bookmovie.Entity.Status;
import com.shashi.bookmovie.Entity.User.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private Status status;

    BigDecimal totalAmount;

    @OneToMany(mappedBy = "booking")
    private List<Seats> seats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showtime_id")
    private ShowTime showTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToOne(mappedBy = "booking" , cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}