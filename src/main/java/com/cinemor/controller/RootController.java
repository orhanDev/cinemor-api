package com.cinemor.controller;

import com.cinemor.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RootController {

    private final MovieRepository movieRepository;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        long movieCount = movieRepository.count();
        return ResponseEntity.ok(Map.of(
                "service", "CinemoR API",
                "movies", "/api/movies",
                "health", "ok",
                "movieCount", movieCount
        ));
    }

    @GetMapping("/api")
    public ResponseEntity<Map<String, Object>> api() {
        long movieCount = movieRepository.count();
        return ResponseEntity.ok(Map.of(
                "service", "CinemoR API",
                "movies", "/api/movies",
                "health", "ok",
                "movieCount", movieCount
        ));
    }
}
