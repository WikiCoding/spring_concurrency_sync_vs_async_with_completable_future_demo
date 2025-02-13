package com.wikicoding.concurrencydemo.services;

import com.wikicoding.concurrencydemo.abstractions.MoviesService;
import com.wikicoding.concurrencydemo.models.Movie;
import com.wikicoding.concurrencydemo.repos.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@AllArgsConstructor
public class ServiceAsync implements MoviesService {
    private final MoviesRepository moviesRepository;

    public CompletableFuture<Iterable<Movie>> getAllMovies() {
        CompletableFuture<Iterable<Movie>> future = new CompletableFuture<>();

        try (ExecutorService executorService = Executors.newFixedThreadPool(32)) {
            executorService.submit(() -> {
                long start = System.currentTimeMillis();
                Iterable<Movie> movies = moviesRepository.findAll();
                long end = System.currentTimeMillis();

                System.out.println("Query took: " + (end - start) + " ms.");

                future.complete(movies);
            });

            return future;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return future;
        }
    }

    public CompletableFuture<Movie> saveMovie(Movie movie) {
        CompletableFuture<Movie> future = new CompletableFuture<>();

        try (ExecutorService executorService = Executors.newFixedThreadPool(32)) {
            executorService.submit(() -> {
                long start = System.currentTimeMillis();
                moviesRepository.save(movie);
                long end = System.currentTimeMillis();
                System.out.println("Movie saved took: " + (end - start) + " ms.");
                future.complete(movie);
            });
            return future;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return future;
        }
    }
}
