package com.example.disney.mapper;

import com.example.disney.dto.response.CharacterBasicResponseDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.entity.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    public static CharacterResponseDto characterToCharacterResponseDto(Character character) {
        CharacterResponseDto characterResponseDto = new CharacterResponseDto();
        characterResponseDto.setId(character.getId()); // maybe might fail
        characterResponseDto.setImage(character.getImage());
        characterResponseDto.setName(character.getName());
        characterResponseDto.setAge(character.getAge());
        characterResponseDto.setWeight(character.getWeight());
        characterResponseDto.setStory(character.getStory());
        characterResponseDto.setDeleted(character.isDeleted()); //new
        //
        characterResponseDto.setMovies(character.getMovies());
        return characterResponseDto;
    }
    // Need to finish
    public static List<CharacterResponseDto> characterToCharacterResponseDtos(List<Character> characters) {
        List<CharacterResponseDto> characterResponseDtos = new ArrayList<>();
        for (Character character: characters) {
            characterResponseDtos.add(characterToCharacterResponseDto(character));
        }
        return characterResponseDtos;
    }

    public static CharacterBasicResponseDto characterToCharacterBasicResponseDto(Character character) {
        CharacterBasicResponseDto CharacterBasicResponseDto = new CharacterBasicResponseDto();
        CharacterBasicResponseDto.setId(character.getId());
        CharacterBasicResponseDto.setImage(character.getImage());
        CharacterBasicResponseDto.setName(character.getName());
        return CharacterBasicResponseDto;
    }

    public static List<CharacterBasicResponseDto> characterToCharacterBasicResponseDtos(List<Character> characters) {
        List<CharacterBasicResponseDto> characterBasicResponseDtos = new ArrayList<>();
        for( Character character: characters) {
            characterBasicResponseDtos.add(characterToCharacterBasicResponseDto(character));
        }
        return characterBasicResponseDtos;
    }
}
