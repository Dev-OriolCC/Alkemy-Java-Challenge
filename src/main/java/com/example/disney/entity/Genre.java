package com.example.disney.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Genre {

    @Id
    @Column(name = "id", length = 12, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String image;

    private boolean deleted = Boolean.FALSE;

    // Movies ->
    //@OneToMany(mappedBy = "genre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

//    @OneToMany(mappedBy = "genre")
//    @JsonIgnore
//    private List<Movie> movies = new ArrayList<>();

}
