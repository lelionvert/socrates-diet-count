package com.lcdlv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lcdlv.Diet.*;

public class DietCalculator {

    public static Map<Diet, Integer> countCover(List<Diet> dietList) {
        Map<Diet, Integer> result = new HashMap<>();
        if (dietList.isEmpty()) {
            result.put(VEGE, 0);
        } else {
            if (dietList.contains(VEGE))
                result.put(VEGE, 1);
            else
                result.put(VEGE, 0);
        }
        return result;
    }
}
