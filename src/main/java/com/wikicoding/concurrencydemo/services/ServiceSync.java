package com.wikicoding.concurrencydemo.services;

import com.wikicoding.concurrencydemo.models.Movie;
import com.wikicoding.concurrencydemo.repos.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceSync {
    private final MoviesRepository moviesRepository;

    public Iterable<Movie> getAllMovies() {
        return moviesRepository.findAll();
    }

    public Movie saveMovie(Movie movie) {
        return moviesRepository.save(movie);
    }
}
