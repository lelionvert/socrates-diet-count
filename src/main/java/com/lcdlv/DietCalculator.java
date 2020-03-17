package com.lcdlv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lcdlv.Diet.*;

public class DietCalculator {

    public static Map<Diet, Integer> countCovers(List<Diet> diets) {
        Map<Diet, Integer> covers = new HashMap<>();

        covers.put(VEGE, coversByDiet(diets, VEGE));
        covers.put(VEGAN, coversByDiet(diets, VEGAN));
        covers.put(PESCE, coversByDiet(diets, PESCE));
        covers.put(OMNI, coversByDiet(diets, OMNI));

        return covers;
    }

    private static int coversByDiet(List<Diet> diets, Diet dietType) {
        return Math.toIntExact(diets.stream().filter(diet -> diet.equals(dietType)).count());
    }
}
