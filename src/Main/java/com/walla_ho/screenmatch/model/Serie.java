package com.walla_ho.screenmatch.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)//name = "title")
    private String title;
    private String year;
    private String released;

    @Enumerated(EnumType.STRING)
    private Category gender;

    private Integer totalSeasons;
    private Double imdbRating;
    private String actors;
    private String summary;
    private String poster;

    @Transient
    //    @OneToMany(mappedBy = "serie")
    private List<Episode> episodes = new ArrayList<>();

    public Serie() { }

    public Serie(DataSerie dataSerie) {
        this.title = dataSerie.title();
        this.year = dataSerie.year();
        this.released = dataSerie.released();
        this.gender = Category.fromString(dataSerie.gender().split(",")[0].trim());
        this.totalSeasons = dataSerie.totalSeasons();
        this.imdbRating = OptionalDouble.of(Double.valueOf(dataSerie.imdbRating())).orElse(0);
        this.actors = dataSerie.actors();
        this.summary = dataSerie.summary();
        this.poster = dataSerie.poster();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Category getGender() {
        return gender;
    }

    public void setGender(Category gender) {
        this.gender = gender;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
    @Override
    public String toString() {
        return "Gender=" + gender +
               ", Title='" + title + '\'' +
               ", Year='" + year + '\'' +
               ", Released='" + released + '\'' +
               ", Total Seasons=" + totalSeasons +
               ", IMdb Rating=" + imdbRating +
               ", Actors='" + actors + '\'' +
               ", Summary='" + summary + '\'' +
               ", Poster='" + poster + '\'';
    }
}
