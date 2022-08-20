package com.example.disney.service;

import com.example.disney.dto.GenreDto;
import com.example.disney.entity.Genre;
import com.example.disney.mapper.GenreMapper;
import com.example.disney.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    public List<GenreDto> getAll() {
        List<Genre> genreList = genreRepository.findAll();
        List<GenreDto> genreDtos = GenreMapper.genreToGenreDtos(genreList);
        return genreDtos;
    }

    @Override
    public GenreDto createGenre(GenreDto genreDto) {
        Genre genre = GenreMapper.genreDtoToGenre(genreDto);
        Genre genreSaved = genreRepository.save(genre);
        GenreDto genreDtoResponse = GenreMapper.genreToGenreDto(genreSaved);
        return genreDtoResponse;
    }

    @Override
    public void deleteGenre(Integer id) {
        genreRepository.deleteById(id);
    }
}
