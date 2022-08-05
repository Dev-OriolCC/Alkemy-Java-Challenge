package com.example.disney.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @Column(name = "id", length = 12, unique = true, nullable = false)
    private Integer id;

    private String image;
    private String title;
    private LocalDate date_created;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    // Characters ->

    // Genre ->
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    private Genre genre;



}
