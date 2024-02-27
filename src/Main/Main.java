package Main;

import honorio.screenmatch.calculations.RecommendationFilter;
import honorio.screenmatch.calculations.TimeCalculator;
import honorio.screenmatch.models.Episode;
import honorio.screenmatch.models.Film;
import honorio.screenmatch.models.Series;
import honorio.screenmatch.models.Title;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Title> list = new ArrayList<>();

        Film film1 = new Film("The Matrix",1999);
        film1.setDurationMinutes(135);
        film1.setIncludedPlan(true);

        Film film2 = new Film("John Wick",2014);
        film2.setDurationMinutes(101);
        film2.setIncludedPlan(true);

        Series serie1 = new Series("La Casa de Papel",2017);
        serie1.setDurationMinutes(135);
        serie1.setIncludedPlan(true);
        serie1.setActive(true);
        serie1.setSeasons(5);
        serie1.setEpisodesPerSeason(10);
        serie1.setMinutesPerEpisodes(45);

        TimeCalculator calculator = new TimeCalculator();
        calculator.add(film1);
        calculator.add(film2);
        calculator.add(serie1);

        System.out.println("Total time: " +calculator.getTotalTime());

        Episode firstEpisode = new Episode();
        firstEpisode.setNumber(1);
        firstEpisode.setSeries(serie1);
        firstEpisode.setTotalViews(300);

        RecommendationFilter filter = new RecommendationFilter();
        filter.filter(film1);
        filter.filter(film2);
        filter.filter(firstEpisode);


        list.add(film1);
        list.add(film2);
        list.add(serie1);

        System.out.println(list.toString());

    }

}
