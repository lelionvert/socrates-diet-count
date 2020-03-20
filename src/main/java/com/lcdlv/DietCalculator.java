package com.lcdlv;

import scenario.Attendee;

import java.util.*;
import java.util.stream.Collectors;

import static scenario.Meal.THURSDAY_EVENING;

public class DietCalculator {

    private Diet[] suggestedDiets;
    private AttendeeParser parser;
    private ColdMealCalculator coldMealCalculator;


    public DietCalculator() {
        this.suggestedDiets = Diet.values();
        //INITIALISER LES COLLABORATEURS
    }

    public DietCalculator(AttendeeParser parser) {
        this.suggestedDiets = Diet.values();
        this.parser = parser;
        // INITIALISER COLD MEAL CALCULATOR
    }

    public DietCalculator(AttendeeParser parser, ColdMealCalculator coldMealCalculator) {
        this(parser);
        this.coldMealCalculator = coldMealCalculator;
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

    public List<Cover> countCoversOfAttendeesWithParser(String input) {
        List<Attendee> attendees = parser.parseAttendees(input);
        return countCoversOfAttendees(attendees);
    }
}
