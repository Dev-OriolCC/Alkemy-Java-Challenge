package com.example.disney.mapper;

import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.dto.response.MovieBasicResponseDto;
import com.example.disney.dto.response.MovieResponseDto;
import com.example.disney.entity.Character;
import com.example.disney.entity.Movie;
import org.springframework.data.convert.Jsr310Converters;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public static MovieResponseDto movieToMovieResponseDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setImage(movie.getImage());
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setRating(movie.getRating());
        movieResponseDto.setDate_created(movie.getDate_created().toString()); // todo Added toString()
        movieResponseDto.setDeleted(movie.isDeleted());
        //
        movieResponseDto.setCharacters(movie.getCharacters());
        return movieResponseDto;
    }

    public static List<MovieResponseDto> movieToMovieResponseDtos(List<Movie> movies) {
        List<MovieResponseDto> movieResponseDtos = new ArrayList<>();
        for (Movie movie: movies) {
            movieResponseDtos.add(movieToMovieResponseDto(movie));
        }
        return movieResponseDtos;
    }

    public static MovieBasicResponseDto movieToMovieBasicResponseDto(Movie movie) {
        MovieBasicResponseDto movieBasicResponseDto = new MovieBasicResponseDto();
        movieBasicResponseDto.setId(movie.getId());
        movieBasicResponseDto.setTitle(movie.getTitle());
        movieBasicResponseDto.setImage(movie.getImage());
        movieBasicResponseDto.setDate_created(movie.getDate_created().toString());
        return movieBasicResponseDto;
    }

    public static List<MovieBasicResponseDto> movieToMovieBasicResponseDtos(List<Movie> movies) {
        List<MovieBasicResponseDto> movieBasicResponseDtos = new ArrayList<>();
        for (Movie movie: movies) {
            movieBasicResponseDtos.add(movieToMovieBasicResponseDto(movie));
        }
        return movieBasicResponseDtos;
    }

}
