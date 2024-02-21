package honorio.screenmatch.models;

import honorio.screenmatch.calculations.Classifiable;

public class Film extends Title implements Classifiable {

    private String director;

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public int getClassification() {
        return (int) getMedia() / 2;
    }
}
