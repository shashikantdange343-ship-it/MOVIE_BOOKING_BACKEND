package com.shashi.bookmovie.Services.Movie;

import com.shashi.bookmovie.DTOs.MovieDTOs.MovieDTOs;
import com.shashi.bookmovie.DTOs.ShowDTOs.TheatreDTOs;
import com.shashi.bookmovie.Entity.Movie.Movie;
import com.shashi.bookmovie.Exceptons.ResourceNotFoundException;
import com.shashi.bookmovie.Repository.Movie.MovieRepository;
import com.shashi.bookmovie.Services.Show.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieClassConverter movieClassConverter;

    private final TheatreService theatreService;

    @Override//FETCHING A MOVIE
    public Movie getMovie(Long movieId) {

       Optional<Movie> optionalMovie = movieRepository.findById(movieId);
       if (optionalMovie.isEmpty()){
           throw new ResourceNotFoundException("Movie Not Found !");
       }
        return optionalMovie.get();

    }


    @Override//CONVERTED INTO MOVIE DTO
    public Page<MovieDTOs.MovieDTO> handleGetAllMovieRequest(int p) {
        return  getAllMovies(p).map(movieClassConverter::movieDTO);
    }


    @Override//PAGE OF MOVIE LIST
    public Page<Movie> getAllMovies(int p) {

        Pageable moviePage = PageRequest.of(p,12);
        return movieRepository.findAll(moviePage);

    }

    @Override//PAGE OF MOVIE PAGE DTO FOR FRONTEND
    public MovieDTOs.OneMoviePageDTO handleOneMoviePageRequest(Long movieId) {

        List<TheatreDTOs.TheatreDTO> theatreDTOS = theatreService.handleGetTheatresOfMovie(movieId);
        Movie movie = getMovie(movieId);

        return new MovieDTOs.OneMoviePageDTO(movieClassConverter.movieDTO(movie),theatreDTOS);
    }


}
