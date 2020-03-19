package scenario;

import com.lcdlv.Diet;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Attendee {
    private Diet diet;

    public Attendee(Diet diet, DayOfWeek arrivalDay, LocalTime arrivalTime) {
        this.diet = diet;
    }

    public boolean isDietOf(Diet suggestedDiet) {
        return diet.equals(suggestedDiet);
    }
}
