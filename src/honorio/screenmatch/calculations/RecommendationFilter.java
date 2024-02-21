package honorio.screenmatch.calculations;

public class RecommendationFilter {
    public void filter(Classifiable classifiable) {
        if (classifiable.getClassification() >= 4) {
            System.out.println("Top movie");
        } else if (classifiable.getClassification() >= 2) {
            System.out.println("Good movie");
        } else {
            System.out.println("See later");
        }
    }
}
