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
        List<Cover> covers = new ArrayList<>();
        for (Diet suggestedDiet : suggestedDiets) {
            long countTotal = attendees.stream().filter(attendee -> attendee.isDietOf(suggestedDiet)).count();
            covers.add(new Cover(suggestedDiet, Math.toIntExact(countTotal), 0, THURSDAY_EVENING));
        }
        return covers;
    }

}
