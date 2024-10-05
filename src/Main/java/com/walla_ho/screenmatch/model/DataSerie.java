package com.walla_ho.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie (@JsonProperty("Title") String title,
                         @JsonProperty("Year") String year,
                         @JsonProperty("Released") String released,
                         @JsonProperty("Genre") String gender,
                         @JsonAlias("totalSeasons") Integer totalSeasons,
                         @JsonAlias("imdbRating") String imdbRating,
                         @JsonProperty("Actors") String actors,
                         @JsonProperty("Plot") String summary,
                         @JsonProperty("Poster") String poster){

}