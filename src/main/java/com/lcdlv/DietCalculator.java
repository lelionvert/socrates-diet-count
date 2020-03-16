package com.lcdlv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lcdlv.Diet.*;

public class DietCalculator {

    public static Map<Diet, Integer> countCover(List<Diet> dietList) {
        Map<Diet, Integer> result = new HashMap<>();
        result.put(VEGE, dietList.size());
        result.put(VEGAN, 0);
        result.put(PESCE, 0);
        result.put(OMNI, 0);

        return result;
    }
}
