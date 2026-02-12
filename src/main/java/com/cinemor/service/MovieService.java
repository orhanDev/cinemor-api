package com.cinemor.service;

import com.cinemor.entity.Movie;
import com.cinemor.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    
    private final MovieRepository movieRepository;
    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }
    
    public List<Movie> getComingSoonMovies() {
        return movieRepository.findByIsComingSoon(true);
    }
    
    public List<Movie> getNowShowingMovies() {
        return movieRepository.findByIsComingSoon(false);
    }
    
    @Transactional
    public Movie saveMovie(Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findByTitle(movie.getTitle());
        if (existingMovie.isPresent()) {
            Movie existing = existingMovie.get();
            existing.setOriginalTitle(movie.getOriginalTitle());
            existing.setGenre(movie.getGenre());
            existing.setDuration(movie.getDuration());
            existing.setDirector(movie.getDirector());
            existing.setCast(movie.getCast());
            existing.setDescription(movie.getDescription());
            existing.setYear(movie.getYear());
            existing.setCountry(movie.getCountry());
            existing.setReleaseDate(movie.getReleaseDate());
            existing.setFsk(movie.getFsk());
            if (movie.getPosterPath() != null && !movie.getPosterPath().isBlank()) {
                existing.setPosterPath(movie.getPosterPath());
            }
            if (movie.getSliderPath() != null && !movie.getSliderPath().isBlank()) {
                existing.setSliderPath(movie.getSliderPath());
            }
            if (movie.getTicketPath() != null && !movie.getTicketPath().isBlank()) {
                existing.setTicketPath(movie.getTicketPath());
            }
            existing.setIsComingSoon(movie.getIsComingSoon());
            return movieRepository.save(existing);
        }
        return movieRepository.save(movie);
    }
    
    @Transactional
    public List<Movie> importMovies(List<Movie> movies) {
        return movies.stream()
                .map(this::saveMovie)
                .toList();
    }
    
    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
