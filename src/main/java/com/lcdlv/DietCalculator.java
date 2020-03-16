package com.lcdlv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lcdlv.Diet.*;

public class DietCalculator {

    public static Map<Diet, Integer> countCover(List<Diet> dietList) {
        Map<Diet, Integer> result = new HashMap<>();

        result.put(VEGE, countBy(dietList, VEGE));
        result.put(VEGAN, countBy(dietList, VEGAN));
        result.put(PESCE, countBy(dietList, PESCE));
        result.put(OMNI, countBy(dietList, OMNI));

        return result;
    }

    private static int countBy(List<Diet> dietList, Diet vege) {
        return Math.toIntExact(dietList.stream().filter(diet -> diet.equals(vege)).count());
    }
}
