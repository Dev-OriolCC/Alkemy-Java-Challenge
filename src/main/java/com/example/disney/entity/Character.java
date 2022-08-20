package com.example.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity()
@Table(name = "charac")
@SQLDelete(sql = "UPDATE charac SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Character {

    @Id
    @Column(name = "id", length = 12, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;
    private String name;
    private Integer age;
    private Float weight;
    private String story;
    // Movies
    //@ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    @ManyToMany(mappedBy = "characters")
    private List<Movie> movies = new ArrayList<>();
    // Soft delete
    private boolean deleted = Boolean.FALSE;


}
