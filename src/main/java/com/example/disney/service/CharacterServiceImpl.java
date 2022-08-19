package com.example.disney.service;

import com.example.disney.dto.CharacterFiltersDto;
import com.example.disney.dto.request.CharacterRequestDto;
import com.example.disney.dto.response.CharacterBasicResponseDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.exception.ParamNotFound;
import com.example.disney.mapper.CharacterMapper;
import com.example.disney.entity.Character;
import com.example.disney.repository.CharacterRepository;
import com.example.disney.repository.specifications.CharacterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterSpecification characterSpecification;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository, CharacterSpecification characterSpecification) {
        this.characterRepository = characterRepository;
        this.characterSpecification = characterSpecification;
    }

    @Override
    public CharacterResponseDto getDetailCharacter(Integer id) {
        Optional<Character> character = characterRepository.findById(id);
        if (!character.isPresent()) { // Otra manera
            throw new ParamNotFound("ERROR: Invalid character ID");
        }
        return CharacterMapper.characterToCharacterResponseDto(character.get());
    }

    @Override
    public CharacterResponseDto createCharacter(CharacterRequestDto characterRequestDto) {
        Character character = CharacterMapper.characterRequestDtoToCharacter(characterRequestDto);
        Character characterSaved = characterRepository.save(character);
        CharacterResponseDto characterResponseDto = CharacterMapper.characterToCharacterResponseDto(characterSaved);
//        Character character = new Character();
//        character.setImage(characterRequestDto.getImage());
//        character.setName(characterRequestDto.getName());
//        character.setAge(characterRequestDto.getAge());
//        character.setWeight(characterRequestDto.getWeight());
//        character.setStory(characterRequestDto.getStory());
        return characterResponseDto;

    }

    @Override
    public Character createCharacterForMovie(CharacterRequestDto characterRequestDto) {
        Character character = new Character();
        character.setImage(characterRequestDto.getImage());
        character.setName(characterRequestDto.getName());
        character.setAge(characterRequestDto.getAge());
        character.setWeight(characterRequestDto.getWeight());
        character.setStory(characterRequestDto.getStory());
        characterRepository.save(character);
        return character;
    }

    @Override
    public CharacterResponseDto updateCharacter(Integer id, CharacterRequestDto characterRequestDto) {
        Optional<Character> characterOptional = characterRepository.findById(id);
        if (!characterOptional.isPresent()) { // Otra manera
            throw new ParamNotFound("ERROR: Invalid character ID");
        }
        Character character = characterOptional.get();
        character.setImage(characterRequestDto.getImage());
        character.setName(characterRequestDto.getName());
        character.setAge(characterRequestDto.getAge());
        character.setWeight(characterRequestDto.getWeight());
        character.setStory(characterRequestDto.getStory());
        characterRepository.save(character);
        return CharacterMapper.characterToCharacterResponseDto(character);
    }

    @Override
    public CharacterResponseDto deleteCharacter(Integer id) {
        //Character character = characterRepository.findById(id).get();
        Optional<Character> character = characterRepository.findById(id);
        if (!character.isPresent()) { // Otra manera
            throw new ParamNotFound("ERROR: Invalid character ID");
        }
        characterRepository.delete(character.get());
        return CharacterMapper.characterToCharacterResponseDto(character.get());
    }


    @Override
    public List<CharacterBasicResponseDto> getByFilters(String name, Integer age, Set<Integer> idMovie) {
        CharacterFiltersDto characterFiltersDto = new CharacterFiltersDto(name, age, idMovie);
        List<Character> characters = characterRepository.findAll(characterSpecification.getByFilters(characterFiltersDto));
        List<CharacterBasicResponseDto> characterResponseDtos = CharacterMapper.characterToCharacterBasicResponseDtos(characters);
        return characterResponseDtos;
    }

    @Override
    public List<CharacterBasicResponseDto> getAllBasic() {
        List<Character> characters = characterRepository.findAll();
        return CharacterMapper.characterToCharacterBasicResponseDtos(characters);
    }
}
