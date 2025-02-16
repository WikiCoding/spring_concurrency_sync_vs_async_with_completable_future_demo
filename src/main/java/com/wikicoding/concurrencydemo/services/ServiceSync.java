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
import java.util.Random;

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
        int totalPartitions = 2;
        int randomPartition = new Random().nextInt(totalPartitions);

        try {
            String jsonConverted = objectMapper.writeValueAsString(saved);
            // null key for random distribution
            String key = String.valueOf(saved.getMovieId());
            template.send("movies-topic", randomPartition, key, jsonConverted);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return saved;
    }
}
