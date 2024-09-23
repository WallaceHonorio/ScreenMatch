package com.walla_ho.screenmatch.model;

public class Film {

    private String title;
    private Integer year;
    private Integer runtime;

    public Film(DataFilm dataFilm) {
        this.title = dataFilm.title();
        this.year = Integer.valueOf(dataFilm.year());
        this.runtime = Integer.valueOf(dataFilm.runtime());
    }

    @Override
    public String toString() {
        return "Film='" + title + '\'' +
                ", year=" + year +
                ", runtime=" + runtime +
                '}';
    }
}