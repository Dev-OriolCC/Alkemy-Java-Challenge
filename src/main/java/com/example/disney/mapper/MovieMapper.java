package com.example.disney.mapper;

import com.example.disney.dto.request.CharacterRequestDto;
import com.example.disney.dto.request.MovieRequestDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.dto.response.MovieBasicResponseDto;
import com.example.disney.dto.response.MovieResponseDto;
import com.example.disney.entity.Character;
import com.example.disney.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired MovieMapper movieMapper;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");


    // Entity -> DTO
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
    // List<Entity> -> List<DTO>
    public static List<MovieResponseDto> movieToMovieResponseDtos(List<Movie> movies) {
        List<MovieResponseDto> movieResponseDtos = new ArrayList<>();
        for (Movie movie: movies) {
            movieResponseDtos.add(movieToMovieResponseDto(movie));
        }
        return movieResponseDtos;
    }

    // DTO -> Entity
    public static Movie movieRequestDtoToMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setImage(movieRequestDto.getImage());
        movie.setTitle(movieRequestDto.getTitle());
        movie.setRating(movieRequestDto.getRating());
        movie.setDate_created(LocalDate.parse(movieRequestDto.getDate_created(), formatter));
        //
        List<Character> characters = CharacterMapper.characterRequestDtoToCharacters(movieRequestDto.getCharacters());
        movie.setCharacters(characters);
        return movie;
    }

    // List<DTO> -> List<Entity>
    public static List<Movie> movieRequestDtoToMovies (List<MovieRequestDto> movieRequestDtos) {
        List<Movie> movies = new ArrayList<>();
        for (MovieRequestDto movieRequestDto: movieRequestDtos) {
            movies.add(movieRequestDtoToMovie(movieRequestDto));
        }
        return movies;
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

    public static void movieRefresh(Movie movie, MovieRequestDto movieRequestDto) {
        movie.setImage(movieRequestDto.getImage());
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDate_created(LocalDate.parse(movieRequestDto.getDate_created(), formatter));
        movie.setRating(movieRequestDto.getRating());
    }

}
