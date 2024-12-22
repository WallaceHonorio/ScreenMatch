package com.walla_ho.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer season;
    private Integer episode;
    private Double imdbRating;
    private LocalDate released;

    @ManyToOne
    private Serie serie;

    public Episode() { }

    public Episode(Integer numberSeason, DataEpisode dataEpisode) {
        this.season = numberSeason;
        this.episode = dataEpisode.episode();
        this.title = dataEpisode.title();

        try{
            this.imdbRating = Double.valueOf(dataEpisode.imdbRating());
        } catch (NumberFormatException ex){
            this.imdbRating = 0.0;
        }

        try{
            this.released = LocalDate.parse(dataEpisode.released());
        } catch (DateTimeParseException ex){
            this.released = LocalDate.ofEpochDay(0);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return  "season=" + season +
                ", episode=" + episode +
                ", title='" + title + '\'' +
                ", imdbRating=" + imdbRating +
                ", released=" + released;
    }
}
