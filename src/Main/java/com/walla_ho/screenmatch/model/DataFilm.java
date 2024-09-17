package com.walla_ho.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataFilm (String title,
                        String year,
                        String runtime) {
}
