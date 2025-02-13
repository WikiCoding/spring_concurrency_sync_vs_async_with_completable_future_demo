package com.wikicoding.concurrencydemo.abstractions;

import com.wikicoding.concurrencydemo.models.Movie;

import java.util.concurrent.CompletableFuture;

public interface MoviesService {
    CompletableFuture<Iterable<Movie>> getAllMovies();
}
