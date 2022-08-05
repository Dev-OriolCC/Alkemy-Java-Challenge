package com.example.disney.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    // Characters *--*
    @ManyToMany()
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "movie_character",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<Character> characters = new HashSet<>();

    // Genre *--1
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    private Genre genre;



}
