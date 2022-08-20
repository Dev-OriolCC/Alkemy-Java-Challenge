package com.example.disney.dto.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
