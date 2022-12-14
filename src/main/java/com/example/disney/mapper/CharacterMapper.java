package com.example.disney.mapper;

import com.example.disney.dto.request.CharacterRequestDto;
import com.example.disney.dto.response.CharacterBasicResponseDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.dto.response.MovieResponseDto;
import com.example.disney.entity.Character;
import com.example.disney.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired CharacterMapper characterMapper;

    // Entity -> DTO
    public static CharacterResponseDto characterToCharacterResponseDto(Character character, boolean showMovies) {
        CharacterResponseDto characterResponseDto = new CharacterResponseDto();
        characterResponseDto.setId(character.getId()); // maybe might fail
        characterResponseDto.setImage(character.getImage());
        characterResponseDto.setName(character.getName());
        characterResponseDto.setAge(character.getAge());
        characterResponseDto.setWeight(character.getWeight());
        characterResponseDto.setStory(character.getStory());
        characterResponseDto.setDeleted(character.isDeleted()); //new
        //
        if(showMovies) {
            List<MovieResponseDto> movieResponseDtos = MovieMapper.movieToMovieResponseDtos(character.getMovies(), false);
            characterResponseDto.setMovies(movieResponseDtos);
        }
        return characterResponseDto;
    }
    // List<Entity> -> List<DTO>
    public static List<CharacterResponseDto> characterToCharacterResponseDtos(List<Character> characters, boolean showMovies) {
        List<CharacterResponseDto> characterResponseDtos = new ArrayList<>();
        for (Character character: characters) {
            characterResponseDtos.add(characterToCharacterResponseDto(character, showMovies));
        }
        return characterResponseDtos;
    }

    // DTO -> Entity
    public static Character characterRequestDtoToCharacter(CharacterRequestDto characterRequestDto, boolean showMovies) {
        Character character = new Character();
        character.setImage(characterRequestDto.getImage());
        character.setName(characterRequestDto.getName());
        character.setAge(characterRequestDto.getAge());
        character.setWeight(characterRequestDto.getWeight());
        character.setStory(characterRequestDto.getStory());
        if (showMovies) {
            List<Movie> movies = MovieMapper.movieRequestDtoToMovies(characterRequestDto.getMovies());
            character.setMovies(movies);
        }
        return character;
    }

    // List<DTO> -> List<Entity>
    public static List<Character> characterRequestDtoToCharacters(List<CharacterRequestDto> characterRequestDtos) {
        List<Character> characters = new ArrayList<>();
        for (CharacterRequestDto characterRequestDto: characterRequestDtos) {
            characters.add(characterRequestDtoToCharacter(characterRequestDto, false));
        }
        return characters;
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

    public static void characterRefresh(Character character, CharacterRequestDto characterRequestDto) {
        character.setImage(characterRequestDto.getImage());
        character.setName(characterRequestDto.getName());
        character.setAge(characterRequestDto.getAge());
        character.setWeight(characterRequestDto.getWeight());
        character.setStory(characterRequestDto.getStory());
    }

}
