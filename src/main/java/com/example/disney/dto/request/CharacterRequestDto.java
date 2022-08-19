package com.example.disney.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CharacterRequestDto {
    private String image;
    @NotNull
    private String name;
    @Min(1)
    private Integer age;
    private Float weight;
    private String story;
    //
    private List<MovieRequestDto> movies;

}
