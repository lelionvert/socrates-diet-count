package com.lcdlv;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lcdlv.Diet.values;

public class DietCalculator {

    public static Map<Diet, Integer> countCovers(List<Diet> covers) {

        return Arrays
                .stream(values())
                .collect(Collectors.toMap(dietType -> dietType, dietType -> coversByDiet(covers, dietType)));

    }

    private static int coversByDiet(List<Diet> covers, Diet dietType) {
        return Math.toIntExact(covers.stream().filter(diet -> diet.equals(dietType)).count());
    }
}
