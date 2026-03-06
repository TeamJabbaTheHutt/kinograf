package org.example.kinograf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieIdL;

    private String imageURL;
    private String title;
    private String description;
    private String duration;
    private String rating;
    private String ageLimit;
}
