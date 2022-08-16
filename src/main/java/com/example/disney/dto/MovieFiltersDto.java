package com.example.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDto {
    private String name;
    private Integer idGenre;
    private String order;

    public MovieFiltersDto(String name, Integer idGenre, String order) {
        this.name = name;
        this.idGenre = idGenre;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

}
