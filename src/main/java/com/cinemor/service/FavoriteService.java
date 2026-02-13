package com.cinemor.service;

import com.cinemor.entity.Favorite;
import com.cinemor.entity.Movie;
import com.cinemor.repository.FavoriteRepository;
import com.cinemor.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, MovieRepository movieRepository) {
        this.favoriteRepository = favoriteRepository;
        public FavoriteService(FavoriteRepository favoriteRepository) {
            this.favoriteRepository = favoriteRepository;
    }

        // Movie entity kaldırıldığı için getFavorites metodu kaldırıldı.

    public List<Long> getFavoriteMovieIds(Long userId) {
        return favoriteRepository.findByUserIdOrderByCreatedAtDesc(userId)
            .stream()
            .map(Favorite::getMovieId)
            .toList();
    }

    @Transactional
    public boolean add(Long userId, Long movieId) {
        if (movieId == null) return false;
        if (favoriteRepository.existsByUserIdAndMovieId(userId, movieId)) return true;
            // Movie entity kaldırıldığı için film kontrolü yapılmıyor.
        Favorite f = new Favorite();
        f.setUserId(userId);
        f.setMovieId(movieId);
        favoriteRepository.save(f);
        return true;
    }

    @Transactional
    public void remove(Long userId, Long movieId) {
        favoriteRepository.deleteByUserIdAndMovieId(userId, movieId);
    }

    public boolean isFavorite(Long userId, Long movieId) {
        return userId != null && movieId != null && favoriteRepository.existsByUserIdAndMovieId(userId, movieId);
    }
}
