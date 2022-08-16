package com.example.disney.service;

import com.example.disney.dto.request.CharacterRequestDto;
import com.example.disney.dto.request.MovieRequestDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.dto.response.MovieBasicResponseDto;
import com.example.disney.dto.response.MovieResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MovieService {
    public MovieResponseDto getDetailMovie(Integer id); // detailed
    public List<MovieBasicResponseDto> getAllBasic();
    public List<MovieBasicResponseDto> getByFilters(String name, Integer idGenre, String order);

    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto);
    public MovieResponseDto updateMovie(Integer id, MovieRequestDto movieRequestDto);
    public MovieResponseDto deleteMovie(Integer id);

}
