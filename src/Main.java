import honorio.screenmatch.models.Film;

public class Main {
    public static void main(String[] args) {
        Film film = new Film();

        film.setName("The Matrix");
        film.setReleaseYear(1999);
        film.setDurationMinutes(135);
        film.setIncludedPlan(true);

        film.showDescription();
        film.evaluate(9);
        film.evaluate(8);
        film.evaluate(9);

        System.out.println("Média de avaliações do filme: " + film.getMedia());
    }

}
