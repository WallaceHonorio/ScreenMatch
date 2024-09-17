package com.walla_ho.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie (@JsonProperty("Title") String title,
                     @JsonProperty("Year") String year,
                     @JsonProperty("Released") String released,
                     @JsonAlias("totalSeasons") int totalSeasons,
                     @JsonAlias("imdbRating") String imdbRating){

}