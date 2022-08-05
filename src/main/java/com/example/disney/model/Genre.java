package com.example.disney.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Genre {

    @Id
    @Column(name = "id", length = 12, unique = true, nullable = false)
    private Integer id;

    private String name;
    private String image;

    // Movies ->
    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private Set<Movie> movies;
}
