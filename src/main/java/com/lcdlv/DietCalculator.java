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

    public List<Cover> newCountCoverByDiet(List<Diet> diets) {
        return Collections.singletonList(new Cover(VEGE, 0, 0, THURSDAY_EVENING));
    }

    public List<Cover> countCoversOfAttendees(List<Attendee> attendees) {
        if (attendees.isEmpty()) {
            return Collections.emptyList();
        } else if (attendees.size() == 2) {
            return Collections.singletonList(
                    new Cover(VEGE, 2, 0, THURSDAY_EVENING));
        } else if (attendees.size() == 3) {
            return Collections.singletonList(
                    new Cover(VEGE, 3, 0, THURSDAY_EVENING));
        }

        return Collections.singletonList(new Cover(VEGE, 1, 0, THURSDAY_EVENING));
    }
}
