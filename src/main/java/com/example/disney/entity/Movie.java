package com.example.disney.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Movie {
    @Id
    @Column(name = "id", length = 12, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;
    private String title;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate date_created;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    // Characters *--*
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "movie_character",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private List<Character> characters = new ArrayList<>();

    // Genre Relation *--1
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)//referencedColumnName = "id", nullable = false)
    private Genre genre;

    // Genre Column in Movie
    @Column(name = "genre_id", nullable = true)
    private Integer genreId;

    private boolean deleted = Boolean.FALSE;



}
