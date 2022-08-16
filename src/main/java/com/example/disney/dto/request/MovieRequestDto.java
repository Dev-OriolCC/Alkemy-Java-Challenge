package com.example.disney.dto.request;

import com.example.disney.entity.Character;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieRequestDto {
    @NotNull
    private String image;
    @NotNull
    private String title;

    private String date_created;

    @Min(1)
    @Max(5)
    private Integer rating;
    //
    private List<CharacterRequestDto> characters;
}
