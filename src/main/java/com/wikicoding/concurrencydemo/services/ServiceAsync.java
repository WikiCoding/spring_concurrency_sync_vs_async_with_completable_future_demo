package com.wikicoding.concurrencydemo.services;

import com.wikicoding.concurrencydemo.abstractions.MoviesService;
import com.wikicoding.concurrencydemo.configs.AsyncConfig;
import com.wikicoding.concurrencydemo.models.Movie;
import com.wikicoding.concurrencydemo.repos.MoviesRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@AllArgsConstructor
public class ServiceAsync implements MoviesService {
    private final MoviesRepository moviesRepository;
    private final ExecutorService execService;
    private final Logger logger = LoggerFactory.getLogger(ServiceAsync.class);

    public CompletableFuture<Iterable<Movie>> getAllMovies() {
        CompletableFuture<Iterable<Movie>> future = new CompletableFuture<>();

        execService.submit(() -> {
            long start = System.currentTimeMillis();
            Iterable<Movie> movies = moviesRepository.findAll();
            long end = System.currentTimeMillis();

            logger.info("Running on Thread {}", Thread.currentThread().getName());
            logger.info("Query took: {} ms.", end - start);

            future.complete(movies);
        });

        return future;
    }

    public CompletableFuture<Movie> saveMovie(Movie movie) {
        CompletableFuture<Movie> future = new CompletableFuture<>();

        execService.submit(() -> {
            long start = System.currentTimeMillis();
            moviesRepository.save(movie);
            long end = System.currentTimeMillis();
            logger.info("Running on Thread {}", Thread.currentThread().getName());
            logger.info("Movie saved took: {} ms.", end - start);
            future.complete(movie);
        });

        return future;
    }
}
