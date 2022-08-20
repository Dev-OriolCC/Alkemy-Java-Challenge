package com.example.disney.service;

import com.example.disney.dto.GenreDto;
import com.example.disney.dto.request.MovieRequestDto;
import com.example.disney.dto.response.MovieResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {

    public List<GenreDto> getAll();
    public GenreDto createGenre(GenreDto genreDto);

    public void deleteGenre(Integer id);


}
