package com.lcdlv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lcdlv.Diet.*;

public class DietCalculator {

    public static Map<Diet, Integer> countCover(List<Diet> dietList) {
        Map<Diet, Integer> result = new HashMap<>();
        long count = dietList.stream().filter(diet -> diet.equals(VEGE)).count();
        result.put(VEGE, Math.toIntExact(count));
        return result;
    }
}
