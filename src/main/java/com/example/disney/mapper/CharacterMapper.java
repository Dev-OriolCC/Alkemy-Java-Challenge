package com.example.disney.mapper;

import com.example.disney.dto.request.CharacterRequestDto;
import com.example.disney.dto.response.CharacterBasicResponseDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.entity.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    // Entity -> DTO
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
    // List<Entity> -> List<DTO>
    public static List<CharacterResponseDto> characterToCharacterResponseDtos(List<Character> characters) {
        List<CharacterResponseDto> characterResponseDtos = new ArrayList<>();
        for (Character character: characters) {
            characterResponseDtos.add(characterToCharacterResponseDto(character));
        }
        return characterResponseDtos;
    }

    // DTO -> Entity
    public static Character characterRequestDtoToCharacter(CharacterRequestDto characterRequestDto) {
        Character character = new Character();
        character.setImage(characterRequestDto.getImage());
        character.setName(characterRequestDto.getName());
        character.setAge(characterRequestDto.getAge());
        character.setWeight(characterRequestDto.getWeight());
        character.setStory(characterRequestDto.getStory());
        return character;
    }

    //  Entity -> DTO -- Basic (id, image, name)
    public static CharacterBasicResponseDto characterToCharacterBasicResponseDto(Character character) {
        CharacterBasicResponseDto CharacterBasicResponseDto = new CharacterBasicResponseDto();
        CharacterBasicResponseDto.setId(character.getId());
        CharacterBasicResponseDto.setImage(character.getImage());
        CharacterBasicResponseDto.setName(character.getName());
        return CharacterBasicResponseDto;
    }

    // List<Entity> -> List<DTO> -- Basic (id, image, name)
    public static List<CharacterBasicResponseDto> characterToCharacterBasicResponseDtos(List<Character> characters) {
        List<CharacterBasicResponseDto> characterBasicResponseDtos = new ArrayList<>();
        for( Character character: characters) {
            characterBasicResponseDtos.add(characterToCharacterBasicResponseDto(character));
        }
        return characterBasicResponseDtos;
    }
}
