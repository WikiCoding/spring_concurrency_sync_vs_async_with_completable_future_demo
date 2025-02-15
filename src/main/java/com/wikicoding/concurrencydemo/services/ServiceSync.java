package com.wikicoding.concurrencydemo.services;

import com.wikicoding.concurrencydemo.models.Movie;
import com.wikicoding.concurrencydemo.repos.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceSync {
    private final MoviesRepository moviesRepository;

    public Iterable<Movie> getAllMovies() {
        return moviesRepository.findAll();
    }

    public List<String> getAllTitles() {
        Iterable<Movie> movies = moviesRepository.findAll();

        List<String> result = new ArrayList<>();
        movies.forEach(movie -> result.add(movie.getTitle()));

        return result;
    }

    public Movie saveMovie(Movie movie) {
        return moviesRepository.save(movie);
    }
}
