package scenario;

import com.lcdlv.Diet;
import com.lcdlv.DietCalculator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.lcdlv.Diet.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DietCoverCountTest {

    @Test
    public void emptyListGivesMapWithEveryDietCount0() {
        List<Diet> diets = new ArrayList<>();
        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 0);
        expectedMap.put(VEGAN, 0);
        expectedMap.put(OMNI, 0);
        expectedMap.put(PESCE, 0);

        Map<Diet, Integer> covers = DietCalculator.countCovers(diets);

        assertThat(covers).isEqualTo(expectedMap);
    }

    @Test
    public void listWithOneVegetarianCount1() {
        List<Diet> diets = new ArrayList<>(Collections.singletonList(VEGE));

        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 1);
        expectedMap.put(VEGAN, 0);
        expectedMap.put(OMNI, 0);
        expectedMap.put(PESCE, 0);

        Map<Diet, Integer> covers = DietCalculator.countCovers(diets);

        assertThat(covers).isEqualTo(expectedMap);
    }

    @Test
    public void listWithTwoVegetarianCount2() {
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, VEGE));

        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 2);
        expectedMap.put(VEGAN, 0);
        expectedMap.put(OMNI, 0);
        expectedMap.put(PESCE, 0);

        Map<Diet, Integer> covers = DietCalculator.countCovers(diets);

        assertThat(covers).isEqualTo(expectedMap);
    }

    @Test
    public void listWithTwoVegeAndOneVegan() {
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, VEGAN, VEGE));

        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 2);
        expectedMap.put(VEGAN, 1);
        expectedMap.put(OMNI, 0);
        expectedMap.put(PESCE, 0);

        Map<Diet, Integer> covers = DietCalculator.countCovers(diets);

        assertThat(covers).isEqualTo(expectedMap);
    }

    @Test
    public void listWithThreeVegeAndTwoVegan() {
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, VEGAN, VEGE, VEGAN, VEGE));

        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 3);
        expectedMap.put(VEGAN, 2);
        expectedMap.put(OMNI, 0);
        expectedMap.put(PESCE, 0);

        Map<Diet, Integer> covers = DietCalculator.countCovers(diets);

        assertThat(covers).isEqualTo(expectedMap);
    }

    @Test
    public void listWithOneVegeTwoVeganTwoOmni() {
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, VEGAN, OMNI, VEGAN, OMNI));

        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 1);
        expectedMap.put(VEGAN, 2);
        expectedMap.put(OMNI, 2);
        expectedMap.put(PESCE, 0);

        Map<Diet, Integer> covers = DietCalculator.countCovers(diets);

        assertThat(covers).isEqualTo(expectedMap);
    }

    @Test
    public void listWithOneVegeTwoOmniTwoPesceNoVegan(){
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, OMNI, OMNI, PESCE, PESCE));

        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 1);
        expectedMap.put(VEGAN, 0);
        expectedMap.put(OMNI, 2);
        expectedMap.put(PESCE, 2);

        Map<Diet, Integer> covers = DietCalculator.countCovers(diets);

        assertThat(covers).isEqualTo(expectedMap);
    }
}
