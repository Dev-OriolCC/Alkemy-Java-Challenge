package com.example.disney.service;

import com.example.disney.dto.MovieFiltersDto;
import com.example.disney.dto.request.CharacterRequestDto;
import com.example.disney.dto.request.MovieRequestDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.dto.response.MovieBasicResponseDto;
import com.example.disney.dto.response.MovieResponseDto;
import com.example.disney.entity.Character;
import com.example.disney.entity.Movie;
import com.example.disney.exception.ParamNotFound;
import com.example.disney.mapper.CharacterMapper;
import com.example.disney.mapper.MovieMapper;
import com.example.disney.repository.MovieRepository;
import com.example.disney.service.specifications.MovieSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieSpecification movieSpecification;
    private final CharacterService characterService;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");


    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieSpecification movieSpecification, CharacterService characterService) {
        this.movieRepository = movieRepository;
        this.movieSpecification = movieSpecification;
        this.characterService = characterService;
    }

    @Override
    public MovieResponseDto getDetailMovie(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()) { // Otra manera
            throw new ParamNotFound("Error: Invalid movie id");
        }
        return MovieMapper.movieToMovieResponseDto(movie.get());
    }

    @Override
    public List<MovieBasicResponseDto> getAllBasic() {
        List<Movie> movies = movieRepository.findAll();
        return MovieMapper.movieToMovieBasicResponseDtos(movies);
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
        Movie movie = new Movie();
        movie.setImage(movieRequestDto.getImage());
        movie.setTitle(movieRequestDto.getTitle());
        //
        movie.setRating(movieRequestDto.getRating());
        // todo Working
        movie.setDate_created(LocalDate.parse(movieRequestDto.getDate_created(), formatter));
        // todo CREATE Characters
        List<CharacterRequestDto> characterRequestDtos = movieRequestDto.getCharacters();
        List<Character> charactersList = new ArrayList<>();
        for (CharacterRequestDto characterRequestDto: characterRequestDtos) {
            Character character = characterService.createCharacterForMovie(characterRequestDto);
            charactersList.add(character);
        }
        // todo ADD to MOVIE
        movie.setCharacters(charactersList);
        // Genre?? Not applies for this challenge I guess
        movieRepository.save(movie);
        return MovieMapper.movieToMovieResponseDto(movie);
    }

    @Override
    public MovieResponseDto updateMovie(Integer id, MovieRequestDto movieRequestDto) {
        Movie movie = movieRepository.findById(id).get();
        movie.setImage(movieRequestDto.getImage());
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDate_created(LocalDate.parse(movieRequestDto.getDate_created(), formatter));
        movie.setRating(movieRequestDto.getRating());
        //
        movieRepository.save(movie);
        return MovieMapper.movieToMovieResponseDto(movie);
    }

    @Override
    public MovieResponseDto deleteMovie(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()) { // Otra manera
            throw new ParamNotFound("Error: Invalid movie id");
        }
        movieRepository.delete(movie.get());
        return MovieMapper.movieToMovieResponseDto(movie.get());
    }

}
