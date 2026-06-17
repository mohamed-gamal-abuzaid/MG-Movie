package com.mo.MG_Movie.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public record MovieDto(
        Long id,
        String title,
        @JsonProperty("overview") String description,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("backdrop_path") String backdropPath,
        @JsonProperty("vote_average") Double rating,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("genre_ids") List<Integer> genreIds,


        Map<String, Object> videos,
        Map<String, Object> credits,
        List<Map<String, Object>> genres
) {}