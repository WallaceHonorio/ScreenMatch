package com.walla_ho.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeason( @JsonProperty("Season") Integer season,
                          @JsonProperty("Episodes") List<DataEpisode> episodes ) {
}
