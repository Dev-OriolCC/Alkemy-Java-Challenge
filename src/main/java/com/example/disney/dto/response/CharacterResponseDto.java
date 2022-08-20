package com.example.disney.dto.response;

import com.example.disney.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CharacterResponseDto {
    private Integer id;
    private String image;
    private String name;
    private Integer age;
    private Float weight;
    private String story;
    private Boolean deleted;
    //
    private List<MovieResponseDto> movies;
}
