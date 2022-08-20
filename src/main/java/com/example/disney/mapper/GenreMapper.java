package com.example.disney.mapper;

import com.example.disney.dto.GenreDto;
import com.example.disney.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    @Autowired GenreMapper genreMapper;

    // Entity -> DTO
    public static GenreDto genreToGenreDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setImage(genre.getImage());
        genreDto.setName(genre.getName());
        return genreDto;
    }

    // List<Entity> -> List<DTO>
    public static List<GenreDto> genreToGenreDtos(List<Genre> genres) {
        List<GenreDto> genreDtos = new ArrayList<>();
        for (Genre genre: genres) {
            genreDtos.add(genreToGenreDto(genre));
        }
        return genreDtos;
    }

    // DTO -> Entity
    public static Genre genreDtoToGenre(GenreDto genreDto) {
        Genre genre = new Genre();
        //.setId(genreDto.getId());
        genre.setName(genreDto.getName());
        genre.setImage(genreDto.getImage());
        return genre;
    }

    // List<DTO> -> List<Entity>
    public static List<Genre>genreDtoToGenres(List<GenreDto> genreDtos) {
        List<Genre> genreList = new ArrayList<>();
        for(GenreDto genreDto: genreDtos) {
            genreList.add(genreDtoToGenre(genreDto));
        }
        return genreList;
    }



}
