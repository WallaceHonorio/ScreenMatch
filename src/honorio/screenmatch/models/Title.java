package honorio.screenmatch.models;
public class Title {

    private String name;
    private int releaseYear;
    private int durationMinutes;
    private boolean includedPlan;
    private double sumEvaluations;
    private double totalReviews;

    public void setSumEvaluations(double sumEvaluations) {
        this.sumEvaluations = sumEvaluations;
    }

    public void setTotalReviews(double totalReviews) {
        this.totalReviews = totalReviews;
    }

    public void showDescription () {
        System.out.println("Name: " + name);
        System.out.println("Release: " + releaseYear);
        System.out.println("Minutes duration: " + durationMinutes);
        System.out.println("Included in Special Plan: " + includedPlan);
    }
    public void evaluate (double evaluate) {
        sumEvaluations += evaluate;
        totalReviews++;
    }
    public double getMedia () {
        return sumEvaluations/totalReviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public boolean isIncludedPlan() {
        return includedPlan;
    }

    public void setIncludedPlan(boolean includedPlan) {
        this.includedPlan = includedPlan;
    }

    public double getSumEvaluations() {
        return sumEvaluations;
    }

    public double getTotalReviews() {
        return totalReviews;
    }
}
