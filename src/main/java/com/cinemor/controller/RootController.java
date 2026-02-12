package com.cinemor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> root() {
        return ResponseEntity.ok(Map.of(
                "service", "CinemoR API",
                "movies", "/api/movies",
                "health", "ok"
        ));
    }

    @GetMapping("/api")
    public ResponseEntity<Map<String, String>> api() {
        return ResponseEntity.ok(Map.of(
                "service", "CinemoR API",
                "movies", "/api/movies",
                "health", "ok"
        ));
    }
}
