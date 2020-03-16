package com.lcdlv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lcdlv.Diet.*;

public class DietCalculator {

    public static Map<Diet, Integer> countCover(List<Diet> dietList) {
        Map<Diet, Integer> result = new HashMap<>();
        long countVegan = dietList.stream().filter(diet -> diet.equals(VEGAN)).count();
        long countVege = dietList.stream().filter(diet -> diet.equals(VEGE)).count();

        result.put(VEGE, Math.toIntExact(countVege));
        result.put(VEGAN, Math.toIntExact(countVegan));
        result.put(PESCE, 0);
        result.put(OMNI, 0);

        return result;
    }
}
