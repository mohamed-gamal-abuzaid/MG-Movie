package com.mo.MG_Movie.controller;

import com.mo.MG_Movie.DTOs.MovieDto;
import com.mo.MG_Movie.DTOs.TmdbResponseDto;
import com.mo.MG_Movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/featured")
    public ResponseEntity<TmdbResponseDto> getFeatured() {
        return ResponseEntity.ok(movieService.getFeaturedMovies());
    }

    @GetMapping("/popular")
    public ResponseEntity<TmdbResponseDto> getPopular(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(movieService.getPopularMovies(page));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<TmdbResponseDto> getTopRated(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(movieService.getTopRatedMovies(page));
    }

    @GetMapping("/search")
    public ResponseEntity<TmdbResponseDto> search(@RequestParam String query, @RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(movieService.searchMovies(query, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieDetails(@PathVariable Long id) {
        MovieDto movieDetails = movieService.getMovieDetails(id);
        return ResponseEntity.ok(movieDetails);
    }

    @GetMapping("/arabic/featured")
    public ResponseEntity<TmdbResponseDto> getFeaturedArabic(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(movieService.getFeaturedArabicMovies(page));
    }

    @GetMapping("/arabic/popular")
    public ResponseEntity<TmdbResponseDto> getPopularArabic(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(movieService.getPopularArabicMovies(page));
    }

    @GetMapping("/arabic/top-rated")
    public ResponseEntity<TmdbResponseDto> getTopRatedArabic(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(movieService.getTopRatedArabicMovies(page));
    }
}
