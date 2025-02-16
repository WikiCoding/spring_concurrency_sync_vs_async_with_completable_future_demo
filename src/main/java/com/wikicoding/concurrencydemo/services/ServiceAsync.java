package com.wikicoding.concurrencydemo.services;

import com.wikicoding.concurrencydemo.abstractions.MoviesService;
import com.wikicoding.concurrencydemo.configs.AsyncConfig;
import com.wikicoding.concurrencydemo.models.Movie;
import com.wikicoding.concurrencydemo.repos.MoviesRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
            Movie saved = moviesRepository.save(movie);
            long end = System.currentTimeMillis();
            logger.info("Running on Thread {}", Thread.currentThread().getName());
            logger.info("Movie saved took: {} ms.", end - start);

            future.complete(saved);
        });

        return future;
    }

    public CompletableFuture<ConcurrentMap<Long, String>> getAllTitles() {
        CompletableFuture<ConcurrentMap<Long, String>> future = new CompletableFuture<>();
        ConcurrentMap<Long, String> titlesMap = new ConcurrentHashMap<>();

        execService.submit(() -> {
            long start = System.currentTimeMillis();
            Iterable<Movie> movies = moviesRepository.findAll();
            long end = System.currentTimeMillis();

            logger.info("Running on Thread {}", Thread.currentThread().getName());
            logger.info("Query took: {} ms.", end - start);

            // Collect CompletableFutures for each map insertion
            List<CompletableFuture<Void>> futures = new ArrayList<>();
            long start1 = System.currentTimeMillis();
            movies.forEach(movie -> {
                CompletableFuture<Void> mapFuture = CompletableFuture.runAsync(() ->
                        titlesMap.put(movie.getMovieId(), movie.getTitle()), execService);
                futures.add(mapFuture);
            });

            // Waiting for all the completable futures to resolve
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenRun(() -> future.complete(titlesMap));

            long end1 = System.currentTimeMillis();

            logger.info("Loop took: {} ms.", end1 - start1);
        });

        return future;
    }
}
