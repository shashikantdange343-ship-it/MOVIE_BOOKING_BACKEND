package com.shashi.bookmovie.Entity.Movie;

import com.shashi.bookmovie.Entity.Show.ShowTime;
import com.shashi.bookmovie.Entity.Show.Theatre;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@BatchSize(size = 20)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String movieName;

    @NonNull
    private String movieDescription;

    private String movieImageUrl;

    @Column(nullable = false)
    private String directorName;

    private LocalDateTime releasedAt;

    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> category;

    @OneToOne(fetch = FetchType.LAZY)
    private MovieDetails movieDetails;

    @OneToMany(mappedBy = "movie")
    private List<ShowTime> showTimes;

    @ManyToMany(mappedBy = "movies")
    private List<Theatre> theatres;

    @CreationTimestamp
    @Column(name = "listed_at", updatable = false)
    private LocalDateTime createdAt;

}