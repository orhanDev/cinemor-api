package com.cinemor.controller;

import com.cinemor.entity.Movie;
import com.cinemor.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MovieController {
    
    private final MovieService movieService;
    
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/coming-soon")
    public ResponseEntity<List<Movie>> getComingSoonMovies() {
        return ResponseEntity.ok(movieService.getComingSoonMovies());
    }
    
    @GetMapping("/now-showing")
    public ResponseEntity<List<Movie>> getNowShowingMovies() {
        return ResponseEntity.ok(movieService.getNowShowingMovies());
    }
    
    @PostMapping("/import")
    public ResponseEntity<Map<String, Object>> importMovies(@RequestBody List<Movie> movies) {
        try {
            List<Movie> importedMovies = movieService.importMovies(movies);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Movies imported successfully",
                    "count", importedMovies.size()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "success", false,
                            "message", "Error importing movies: " + e.getMessage()
                    ));
        }
    }
    
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movieService.saveMovie(movie));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.getMovieById(id)
                .map(existing -> {
                    movie.setId(id);
                    return ResponseEntity.ok(movieService.saveMovie(movie));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
