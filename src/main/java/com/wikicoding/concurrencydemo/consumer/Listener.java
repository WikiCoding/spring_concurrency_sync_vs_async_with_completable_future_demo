package com.wikicoding.concurrencydemo.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wikicoding.concurrencydemo.models.Movie;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

@Component
@AllArgsConstructor
public class Listener {
    private final ExecutorService execService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(Listener.class);

    @KafkaListener(id = "consumer-1", topics = "movies-topic",
            topicPartitions = { @TopicPartition(topic = "movies-topic", partitions = { "0" }) })
    public synchronized void listenerPartition0(String message, Acknowledgment acknowledgment) {
        logger.info("Received message from partition 0: {}", message);
        ConcurrentMap<Long, Movie> movies = new ConcurrentHashMap<>();

        execService.submit(() -> {
            // parallel processing
            logger.info("Running on Thread {}", Thread.currentThread().getName());
            Movie movie;
            try {
               movie = objectMapper.readValue(message, Movie.class);
               movies.put(movie.getMovieId(), movie);
               logger.info("Commiting offset / Ack of the message");
               acknowledgment.acknowledge();
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }
        });
    }

    @KafkaListener(id = "consumer-2", topics = "movies-topic",
            topicPartitions = { @TopicPartition(topic = "movies-topic", partitions = { "1" }) })
    public void listenerPartition1(String message) {
        // since there's no ACK the offsets of this partition will not be commited, so on app restart the messages will pop up again
        logger.info("Received message from partition 1: {}", message);
    }
}
