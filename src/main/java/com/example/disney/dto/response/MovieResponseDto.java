package com.example.disney.dto.response;


import com.example.disney.entity.Character;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieResponseDto {

    private Integer id;
    private String image;
    private String title;
    private String date_created;
    private Integer rating;
    private Boolean deleted;
    //
    private List<Character> characters;


}
