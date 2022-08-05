package com.example.disney.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "\"character\"")
public class Character {

    @Id
    @Column(name = "id", length = 12, unique = true, nullable = false)
    private Integer id;

    private String image;
    private String name;
    private Integer age;
    private Float weight;
    private String story;
    // Movies->


}
