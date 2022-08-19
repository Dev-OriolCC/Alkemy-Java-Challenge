package com.example.disney.controller;

import com.example.disney.dto.request.MovieRequestDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.dto.response.MovieBasicResponseDto;
import com.example.disney.dto.response.MovieResponseDto;
import com.example.disney.entity.Movie;
import com.example.disney.mapper.MovieMapper;
import com.example.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // /**
    // * @Return Response Basic Movie Entity (name, image and date_created)
    // */
    @GetMapping()
    public ResponseEntity<List<MovieBasicResponseDto>> getAllByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer idGenre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieBasicResponseDto> movies = movieService.getByFilters(name, idGenre, order);
        return ResponseEntity.ok().body(movies);
    }

    // /**
    // * @Return Response Entire Movie Entity with Characters
    // */
    @GetMapping("/{id}") // All characters with details
    public ResponseEntity<MovieResponseDto> getAllDetail(@PathVariable Integer id) {
        MovieResponseDto movie = movieService.getDetailMovie(id);
        return ResponseEntity.ok().body(movie);
    } // todo pending test to verify

    // /**
    // * @Return Response Entire Movie Entity with Characters
    // */
    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@Valid @RequestBody MovieRequestDto movieRequestDto) {
        MovieResponseDto movieResponseDto = movieService.createMovie(movieRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponseDto);
    }

    // /**
    // * @Return Response Entire Movie Entity with Characters
    // */
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable Integer id, @Valid @RequestBody MovieRequestDto movieRequestDto) {
        MovieResponseDto movieResponseDto = movieService.updateMovie(id, movieRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieResponseDto);
    }

    // /**
    // * @Return Response Empty
    // */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
