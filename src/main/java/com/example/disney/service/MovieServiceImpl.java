package com.example.disney.service;

import com.example.disney.dto.MovieFiltersDto;
import com.example.disney.dto.request.MovieRequestDto;
import com.example.disney.dto.response.MovieBasicResponseDto;
import com.example.disney.dto.response.MovieResponseDto;
import com.example.disney.entity.Movie;
import com.example.disney.exception.ParamNotFound;
import com.example.disney.mapper.MovieMapper;
import com.example.disney.repository.MovieRepository;
import com.example.disney.repository.specifications.MovieSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieSpecification movieSpecification;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieSpecification movieSpecification) {
        this.movieRepository = movieRepository;
        this.movieSpecification = movieSpecification;
    }

    @Override
    public MovieResponseDto getDetailMovie(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new ParamNotFound("ERROR: Invalid movie ID");
        }
        MovieResponseDto movieResponseDto = MovieMapper.movieToMovieResponseDto(movie.get(), true);
        return movieResponseDto;
    }

    @Override
    public List<MovieBasicResponseDto> getAllBasic() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieBasicResponseDto> movieBasicResponseDtos = MovieMapper.movieToMovieBasicResponseDtos(movies);
        return movieBasicResponseDtos;
    }

    @Override
    public List<MovieBasicResponseDto> getByFilters(String name, Integer idGenre, String order) {
        MovieFiltersDto movieFiltersDto = new MovieFiltersDto(name, idGenre, order);
        List<Movie> movies = movieRepository.findAll(movieSpecification.getByFilters(movieFiltersDto));
        List<MovieBasicResponseDto> movieResponseDtos = MovieMapper.movieToMovieBasicResponseDtos(movies);
        return movieResponseDtos;
    }

    @Override
    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto) {
        // Genre?? Not applies for this challenge I guess

        Movie movie = MovieMapper.movieRequestDtoToMovie(movieRequestDto, true);
        Movie movieSaved = movieRepository.save(movie);
        MovieResponseDto movieResponseDto = MovieMapper.movieToMovieResponseDto(movieSaved, true);
        return movieResponseDto;
    }

    @Override
    public MovieResponseDto updateMovie(Integer id, MovieRequestDto movieRequestDto) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(!movieOptional.isPresent()) {
            throw new ParamNotFound("ERROR: Invalid movie ID");
        }
        MovieMapper.movieRefresh(movieOptional.get(), movieRequestDto);
        Movie movie = movieRepository.save(movieOptional.get());
        MovieResponseDto movieResponseDto = MovieMapper.movieToMovieResponseDto(movie, false);
        return movieResponseDto;
    }

    @Override
    public MovieResponseDto deleteMovie(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new ParamNotFound("ERROR: Invalid movie ID");
        }
        movieRepository.delete(movie.get());
        MovieResponseDto movieResponseDto = MovieMapper.movieToMovieResponseDto(movie.get(), false);
        return movieResponseDto;
    }

}
