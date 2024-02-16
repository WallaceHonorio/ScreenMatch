import honorio.screenmatch.calculations.TimeCalculator;
import honorio.screenmatch.models.Film;
import honorio.screenmatch.models.Series;

public class Main {
    public static void main(String[] args) {

        Film film1 = new Film();
        film1.setName("The Matrix");
        film1.setReleaseYear(1999);
        film1.setDurationMinutes(135);
        film1.setIncludedPlan(true);

        Film film2 = new Film();
        film2.setName("John Wick");
        film2.setReleaseYear(2014);
        film2.setDurationMinutes(101);
        film2.setIncludedPlan(true);

        Series serie1 = new Series();
        serie1.setName("La Casa de Papel");
        serie1.setReleaseYear(2017);
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
    }

}
