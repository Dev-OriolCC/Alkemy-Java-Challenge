package com.example.disney.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieBasicResponseDto {
    private Integer id;
    private String title;
    private String image;
    private String date_created;
}
