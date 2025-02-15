package com.wikicoding.concurrencydemo.controllers;

import com.wikicoding.concurrencydemo.abstractions.MoviesService;
import com.wikicoding.concurrencydemo.models.Movie;
import com.wikicoding.concurrencydemo.repos.MoviesRepository;
import com.wikicoding.concurrencydemo.services.ServiceAsync;
import com.wikicoding.concurrencydemo.services.ServiceSync;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesController {
    private final ServiceSync serviceSync;
    private final ServiceAsync serviceAsync;

    @GetMapping("/sync")
    public ResponseEntity<Iterable<Movie>> getAllMovies() {
        return ResponseEntity.ok(serviceSync.getAllMovies());
    }

    @GetMapping("/sync/titles")
    public ResponseEntity<List<String>> getAllTitles() {
        return ResponseEntity.ok(serviceSync.getAllTitles());
    }

    @PostMapping("/sync")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(serviceSync.saveMovie(movie));
    }

    @GetMapping("/async")
    public ResponseEntity<Iterable<Movie>> getAllMoviesAsync() {
        CompletableFuture<Iterable<Movie>> movieCompletableFuture = serviceAsync.getAllMovies();

        return ResponseEntity.ok(movieCompletableFuture.join());
    }

    @GetMapping("/async/titles")
    public ResponseEntity<ConcurrentMap<Long, String>> getAllTitlesAsync() {
        CompletableFuture<ConcurrentMap<Long, String>> movieCompletableFuture = serviceAsync.getAllTitles();

        return ResponseEntity.ok(movieCompletableFuture.join());
    }

    @PostMapping("/async")
    public ResponseEntity<Movie> saveMovieAsync(@RequestBody Movie movie) {
        CompletableFuture<Movie> movieCompletableFuture = serviceAsync.saveMovie(movie);
        Movie movie1 = movieCompletableFuture.join();
        return ResponseEntity.ok(movie1);
    }
}
