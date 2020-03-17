package com.lcdlv;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lcdlv.Diet.values;

public class DietCalculator {

    private static int countCovers(List<Diet> diets, Diet chosenDiet) {
        return Math.toIntExact(diets.stream().filter(diet -> diet.equals(chosenDiet)).count());
    }

    public Map<Diet, Integer> countCoversByDiet(List<Diet> diets) {

        return Arrays
                .stream(values())
                .collect(Collectors.toMap(diet -> diet, diet -> countCovers(diets, diet)));

    }
}
