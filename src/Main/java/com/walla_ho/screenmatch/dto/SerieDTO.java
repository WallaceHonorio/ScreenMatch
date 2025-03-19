package com.walla_ho.screenmatch.dto;

import com.walla_ho.screenmatch.model.Category;

public record SerieDTO(Long id,
                       String title,
                       String year,
                       String released,
                       Category gender,
                       Integer totalSeasons,
                       Double imdbRating,
                       String actors,
                       String summary,
                       String poster) {

}
