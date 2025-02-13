package com.wikicoding.concurrencydemo.repos;

import com.wikicoding.concurrencydemo.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {
}
