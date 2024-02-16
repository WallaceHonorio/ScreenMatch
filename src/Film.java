public class Film {

    String name;
    int releaseYear;
    int durationMinutes;
    boolean includedPlan;
    double sumEvaluations;
    double totalReviews;

    void showDescription () {
        System.out.println("Name: " + name);
        System.out.println("Release: " + releaseYear);
        System.out.println("Minutes duration: " + durationMinutes);
        System.out.println("Included in Special Plan: " + includedPlan);
    }

    void evaluate (double evaluate) {
        sumEvaluations += evaluate;
        totalReviews++;
    }
    double getMedia () {
        return sumEvaluations/totalReviews;
    }

}
