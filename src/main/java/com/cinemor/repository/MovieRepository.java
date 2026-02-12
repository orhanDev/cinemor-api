package com.cinemor.repository;

import com.cinemor.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    Optional<Movie> findByTitle(String title);
    
    List<Movie> findByIsComingSoon(Boolean isComingSoon);
    
    Page<Movie> findByIsComingSoon(Boolean isComingSoon, Pageable pageable);
    
    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:keyword% OR m.originalTitle LIKE %:keyword%")
    List<Movie> searchByKeyword(String keyword);
    
    Page<Movie> findAll(Pageable pageable);
}
