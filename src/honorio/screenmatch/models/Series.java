package honorio.screenmatch.models;

import honorio.screenmatch.calculations.Classifiable;

public class Series extends Title {
    private int seasons;
    private boolean active;
    private int episodesPerSeason;
    private int minutesPerEpisodes;

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getEpisodesPerSeason() {
        return episodesPerSeason;
    }

    public void setEpisodesPerSeason(int episodesPerSeason) {
        this.episodesPerSeason = episodesPerSeason;
    }

    public int getMinutesPerEpisodes() {
        return minutesPerEpisodes;
    }

    public void setMinutesPerEpisodes(int minutesPerEpisodes) {
        this.minutesPerEpisodes = minutesPerEpisodes;
    }

    @Override
    public int getDurationMinutes() {
        return seasons * episodesPerSeason * minutesPerEpisodes;
    }


}
