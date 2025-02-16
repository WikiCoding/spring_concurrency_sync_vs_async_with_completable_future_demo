package com.wikicoding.concurrencydemo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wikicoding.concurrencydemo.models.Movie;
import com.wikicoding.concurrencydemo.repos.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceSync {
    private final MoviesRepository moviesRepository;
    private final KafkaTemplate<String, String> template;
    private final ObjectMapper objectMapper;

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
        Movie saved = moviesRepository.save(movie);

        try {
            String jsonConverted = objectMapper.writeValueAsString(saved);
            template.send("movies-topic", jsonConverted);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return saved;
    }
}
