package com.lcdlv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DietCalculator {

    public static Map<Diet, Integer> countCover(List<Diet> dietList) {
        Map<Diet, Integer> result = new HashMap<>();
        if (dietList.isEmpty()) {
            result.put(Diet.VEGGE, 0);
        } else {
            result.put(Diet.VEGGE, 1);
        }
        return result;
    }
}
