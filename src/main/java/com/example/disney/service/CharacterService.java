package com.example.disney.service;

import com.example.disney.dto.request.CharacterRequestDto;
import com.example.disney.dto.response.CharacterBasicResponseDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.entity.Character;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CharacterService {
    //
    public CharacterResponseDto getDetailCharacter(Integer id);
    public CharacterResponseDto createCharacter(CharacterRequestDto characterRequestDto);
    public CharacterResponseDto updateCharacter(Integer id, CharacterRequestDto characterRequestDto);
    public CharacterResponseDto deleteCharacter(Integer id);

    public Character createCharacterForMovie(CharacterRequestDto characterRequestDto);

    List<CharacterBasicResponseDto> getByFilters(String name, Integer age, Set<Integer> idMovie);

    List<CharacterBasicResponseDto> getAllBasic();
}
