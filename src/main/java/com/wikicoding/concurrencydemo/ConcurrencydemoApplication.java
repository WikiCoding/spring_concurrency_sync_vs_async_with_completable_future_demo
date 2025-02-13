package com.wikicoding.concurrencydemo;

import com.wikicoding.concurrencydemo.models.Movie;
import com.wikicoding.concurrencydemo.repos.MoviesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConcurrencydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencydemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MoviesRepository repository) {
		return args -> {
			for (int i = 0; i < 10_000; i++) {
				repository.save(new Movie("title" + i, "dir" + 1, 2000, 10 + i));
			}
		};
	}
}
