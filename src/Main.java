public class Main {
    public static void main(String[] args) {
        Film film = new Film();

        film.name = "The Matrix";
        film.releaseYear = 1999;
        film.durationMinutes = 135;
        film.includedPlan = true;

        film.showDescription();
        film.evaluate(9);
        film.evaluate(8);
        film.evaluate(9);

        System.out.println("Média de avaliações do filme: " + film.getMedia());
    }

}
