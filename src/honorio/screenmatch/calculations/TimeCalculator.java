package honorio.screenmatch.calculations;

import honorio.screenmatch.models.Title;

public class TimeCalculator {
    private int totalTime;

    public void add(Title title) {
        totalTime += title.getDurationMinutes();
    }

    public int getTotalTime() {
        return totalTime;
    }
}
