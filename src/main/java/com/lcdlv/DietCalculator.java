package com.lcdlv;

import scenario.Attendee;

import java.util.*;
import java.util.stream.Collectors;

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
        if (suggestedDiets.length > 1) {
            List<Cover> covers = new ArrayList<>();
            for (int i = 0; i < suggestedDiets.length; i++) {
                if (attendees.get(0).isDietOf(suggestedDiets[i])) {
                    covers.add(new Cover(suggestedDiets[i], attendees.size(), 0, THURSDAY_EVENING));
                } else {
                    covers.add(new Cover(suggestedDiets[i], 0, 0, THURSDAY_EVENING));
                }
            }
            return covers;
        }
        return Collections.singletonList(new Cover(suggestedDiets[0], attendees.size(), 0, THURSDAY_EVENING));
    }

}
