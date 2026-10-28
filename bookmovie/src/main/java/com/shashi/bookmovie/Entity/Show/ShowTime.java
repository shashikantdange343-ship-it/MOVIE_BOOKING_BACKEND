package com.shashi.bookmovie.Entity.Show;

import com.shashi.bookmovie.Entity.Book.Booking;
import com.shashi.bookmovie.Entity.Movie.Category;
import com.shashi.bookmovie.Entity.Movie.Movie;
import com.shashi.bookmovie.Entity.Movie.MovieDetails;
import com.shashi.bookmovie.Entity.Show.ShowTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "show_time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private LocalDateTime showTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @OneToMany(mappedBy = "showTime")
    private List<Seats> seats;

    private Integer totalSeats;

    private Integer availableSeats;

    @OneToMany(mappedBy = "showTime")
    private List<Booking> booking;

    @CreationTimestamp
    @Column(name = "listed_at", updatable = false)
    private LocalDateTime listedAt;

}