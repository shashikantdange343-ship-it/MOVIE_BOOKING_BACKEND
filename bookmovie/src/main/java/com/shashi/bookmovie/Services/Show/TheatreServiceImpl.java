package com.shashi.bookmovie.Services.Show;

import com.shashi.bookmovie.DTOs.ShowDTOs.TheatreDTOs;
import com.shashi.bookmovie.Entity.Show.Theatre;
import com.shashi.bookmovie.Exceptons.ResourceNotFoundException;
import com.shashi.bookmovie.Repository.Show.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    private final ShowClassConverter showClassConverter;

    @Override
    public Theatre getTheatre(Long theatreId) {
       Optional<Theatre> optionalTheatre = theatreRepository.findById(theatreId);
       if (optionalTheatre.isEmpty()){
           throw new ResourceNotFoundException("Theatre Not Found ! ");
       }
        return optionalTheatre.get();
    }

    @Override//PAGE OF THEATRE TO DESIDE MOVIE ON THE BASE OF THEATRE
    public Page<Theatre> getAllTheatres(int p) {

        Pageable theatresPage = PageRequest.of(p, 12);
        return theatreRepository.findAll(theatresPage);

    }

    @Override//LIST OF THEATRE SHOWING A SPECIFIC MOVIE
    public List<Theatre> getOneMovieTheatres(Long movieId) {

        Optional<List<Theatre>> optionalTheatres = theatreRepository.findByMoviesId(movieId);
        if (optionalTheatres.isEmpty()){
            throw new ResourceNotFoundException("No Theatres Found !");
        }
        return optionalTheatres.get();
    }

    @Override//CONVERTING LIST OF THEATRE TO LIST OF THEATRE DTO
    public List<TheatreDTOs.TheatreDTO> handleGetTheatresOfMovie(Long movieId) {
        return getOneMovieTheatres(movieId).stream().map(showClassConverter::theatreDTO).toList();
    }

    @Override//CONVERTING PAGE OF THEATRE INTO PAGE OF THEATRE DTO
    public Page<TheatreDTOs.TheatreDTO> handleGetAllTheatres(int p) {
        return getAllTheatres(p).map(showClassConverter::theatreDTO);
    }

    @Override
    public Page<TheatreDTOs.TheatreDTO> handleSearchTheatre(int p, String theatreName) {
        Pageable theatrePage = PageRequest.of(p , 12 );
        return theatreRepository.findTheatresByName(theatreName,theatrePage).map(showClassConverter::theatreDTO);
    }

    @Override
    public TheatreDTOs.TheatreDetailsDTO handleGetTheatreDetails(Long theatreId) {
        return showClassConverter.theatreDetailsDTO(getTheatre(theatreId));
    }


}
