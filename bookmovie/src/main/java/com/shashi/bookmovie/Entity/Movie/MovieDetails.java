package com.shashi.bookmovie.Entity.Movie;

import com.shashi.bookmovie.Entity.Show.ShowTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "movie_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ElementCollection
    @Fetch(FetchMode.SUBSELECT)
    private List<String> producerName;

    @ElementCollection
    @Fetch(FetchMode.SUBSELECT)
    private List<String> movieImageUrl;

    @ElementCollection
    @Fetch(FetchMode.SUBSELECT)
    private List<String> movieTrailerUrl;

    @ElementCollection
    @Fetch(FetchMode.SUBSELECT)
    private List<String> movieSongs;

    @ElementCollection
    @Fetch(FetchMode.SUBSELECT)
    private List<String> actors;

    @ElementCollection
    @Fetch(FetchMode.SUBSELECT)
    private List<String> actress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}