package com.example.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CharacterFiltersDto {
    private String name;
    private Integer age;
    private Set<Integer> idMovie;

    public CharacterFiltersDto(String name, Integer age, Set<Integer> idMovie) {
        this.name = name;
        this.age = age;
        this.idMovie = idMovie;
    }
}
