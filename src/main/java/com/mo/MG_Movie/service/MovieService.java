package com.mo.MG_Movie.service;

import com.mo.MG_Movie.DTOs.MovieDto;
import com.mo.MG_Movie.DTOs.TmdbResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Year;

@Service
public class MovieService {

    private final RestClient restClient;

    @Value("${tmdb.api.key}")
    private String apiKey;


    public MovieService(@Value("${tmdb.api.url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }


    public TmdbResponseDto getFeaturedMovies() {
        return restClient.get()
                .uri("/movie/now_playing?api_key=" + apiKey + "&language=en-US&page=1")
                .retrieve()
                .body(TmdbResponseDto.class);
    }


    public TmdbResponseDto getPopularMovies(int page) {
        return restClient.get()
                .uri("/movie/popular?api_key=" + apiKey + "&language=en-US&page=" + page)
                .retrieve()
                .body(TmdbResponseDto.class);
    }


    public TmdbResponseDto getTopRatedMovies(int page) {
        return restClient.get()
                .uri("/movie/top_rated?api_key=" + apiKey + "&language=en-US&page=" + page)
                .retrieve()
                .body(TmdbResponseDto.class);
    }


    public TmdbResponseDto searchMovies(String query, int page) {
        return restClient.get()
                .uri("/search/movie?api_key=" + apiKey + "&query=" + query + "&language=en-US&page=" + page)
                .retrieve()
                .body(TmdbResponseDto.class);
    }


    public MovieDto getMovieDetails(Long id) {
        try {
            return restClient.get()
                    .uri("/movie/" + id + "?api_key=" + apiKey + "&append_to_response=videos,credits")
                    .retrieve()
                    .body(MovieDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch movie details from TMDB for ID: " + id, e);
        }
    }


    public TmdbResponseDto getFeaturedArabicMovies(int page) {
        int currentYear = Year.now().getValue();
        int lastYear = currentYear - 2;


        String fromDate = lastYear + "-01-01";
        String toDate = currentYear + "-12-31";

        return restClient.get()
                .uri("/discover/movie?api_key=" + apiKey
                        + "&with_original_language=ar"
                        + "&region=EG"
                        + "&primary_release_date.gte=" + fromDate
                        + "&primary_release_date.lte=" + toDate
                        + "&sort_by=primary_release_date.desc"
                        + "&vote_count.gte=10"
                        + "&language=ar&page=" + page)
                .retrieve()
                .body(TmdbResponseDto.class);
    }


    public TmdbResponseDto getPopularArabicMovies(int page) {
        return restClient.get()
                .uri("/discover/movie?api_key=" + apiKey
                        + "&with_original_language=ar"
                        + "&region=EG"
                        + "&sort_by=popularity.desc"
                        + "&vote_count.gte=10"
                        + "&language=ar&page=" + page)
                .retrieve()
                .body(TmdbResponseDto.class);
    }

    public TmdbResponseDto getTopRatedArabicMovies(int page) {
        return restClient.get()
                .uri("/discover/movie?api_key=" + apiKey
                        + "&with_original_language=ar"
                        + "&region=EG"
                        + "&sort_by=vote_average.desc"
                        + "&vote_count.gte=10"
                        + "&language=ar&page=" + page)
                .retrieve()
                .body(TmdbResponseDto.class);
    }


}