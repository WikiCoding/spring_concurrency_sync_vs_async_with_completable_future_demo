package com.wikicoding.concurrencydemo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AsyncConfig {

    @Bean
    public ExecutorService execService() {
        return Executors.newFixedThreadPool(32);
    }
}
