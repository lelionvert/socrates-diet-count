package com.lcdlv;

import scenario.Attendee;

import java.util.*;
import java.util.stream.Collectors;

import static com.lcdlv.Diet.VEGAN;
import static com.lcdlv.Diet.VEGE;
import static scenario.Meal.THURSDAY_EVENING;

public class DietCalculator {

    private final Diet[] suggestedDiets;

    public DietCalculator(Diet[] suggestedDiets) {
        this.suggestedDiets = suggestedDiets;
    }

    private static int countCovers(List<Diet> diets, Diet chosenDiet) {
        return Math.toIntExact(diets.stream().filter(diet -> diet.equals(chosenDiet)).count());
    }

    public Map<Diet, Integer> countCoversByDiet(List<Diet> diets) {

        return Arrays
                .stream(suggestedDiets)
                .collect(Collectors.toMap(diet -> diet, diet -> countCovers(diets, diet)));

    }

    public List<Cover> countCoversOfAttendees(List<Attendee> attendees) {
        if (attendees.isEmpty()) {
            return Collections.emptyList();
        }
        if (suggestedDiets.length == 2){
            return Arrays.asList(
                    new Cover(VEGE, 0, 0, THURSDAY_EVENING),
                    new Cover(VEGAN, 1, 0, THURSDAY_EVENING)
            );
        }
        return Collections.singletonList(new Cover(suggestedDiets[0], attendees.size(), 0, THURSDAY_EVENING));
    }
}
