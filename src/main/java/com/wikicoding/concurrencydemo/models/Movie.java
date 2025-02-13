package com.wikicoding.concurrencydemo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;
    private String title;
    private String director;
    private int year;
    private long duration;

    public Movie(String title, String director, int year, long duration) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.duration = duration;
    }
}
