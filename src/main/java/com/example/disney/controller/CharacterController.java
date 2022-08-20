package com.example.disney.controller;

import com.example.disney.dto.request.CharacterRequestDto;
import com.example.disney.dto.response.CharacterBasicResponseDto;
import com.example.disney.dto.response.CharacterResponseDto;
import com.example.disney.entity.Movie;
import com.example.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    // /**
    // * @Return Response Entire Character Entity with Movies
    // */
    @GetMapping("/{id}") // All characters with details
    public ResponseEntity<CharacterResponseDto> getAllDetail(@PathVariable Integer id) {
        CharacterResponseDto characterResponseDto = characterService.getDetailCharacter(id);
        return ResponseEntity.ok().body(characterResponseDto);
    }

    // /**
    // * @Return Response Character Entity (name and image)
    // */
    @GetMapping
    public ResponseEntity<List<CharacterBasicResponseDto>> getAllByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Integer> idMovie
    ) {
        //List<CharacterResponseDto> characters = characterService.getAll();
        List<CharacterBasicResponseDto> characters = characterService.getByFilters(name, age, idMovie);
        return ResponseEntity.ok().body(characters);
    }


    // /**
    // * @Return Response Character Entity with Movies
    // */
    @PostMapping
    public ResponseEntity<CharacterResponseDto> create(@Valid @RequestBody CharacterRequestDto characterRequestDto) {
        CharacterResponseDto characterResponseDto = characterService.createCharacter(characterRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterResponseDto);
    }

    // /**
    // * @Return Response Character Entity with Movies
    // */
    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponseDto> update(@PathVariable Integer id,@Valid @RequestBody CharacterRequestDto characterRequestDto) {
        CharacterResponseDto characterUpdate = characterService.updateCharacter(id, characterRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterUpdate);
    }

    // /**
    // * @Return Response empty
    // */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
