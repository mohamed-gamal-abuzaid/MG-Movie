package com.mo.MG_Movie.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TmdbResponseDto(
        int page,
        List<MovieDto> results,
        @JsonProperty("total_pages") int totalPages,
        @JsonProperty("total_results") int totalResults
) {}