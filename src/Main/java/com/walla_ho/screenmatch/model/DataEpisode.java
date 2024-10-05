package com.walla_ho.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisode(@JsonProperty("Title") String title,
                          @JsonProperty("Season") Integer season,
                          @JsonProperty("Episode") Integer episode,
                          @JsonProperty("Released") String released,
                          @JsonProperty("imdbRating") String imdbRating,
                          @JsonProperty("Director") String director,
                          @JsonProperty("Actors") String actors) {
}
