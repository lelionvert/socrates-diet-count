package scenario;

import com.lcdlv.Diet;
import com.lcdlv.DietCalculator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.lcdlv.Diet.*;
import static org.assertj.core.api.Assertions.assertThat;

public class VeggetarianCountTest {

    @Test
    public void testEnv() {
        assertThat(true).isTrue();
    }

    @Test
    public void emptyListGivesMapWithVegetarianCount0() {
        List<Diet> list = new ArrayList<>();

        Map<Diet, Integer> cover = DietCalculator.countCover(list);

        assertThat(cover.get(VEGE)).isEqualTo(0);
    }

    @Test
    public void listWithOneVegetarianCount1(){
        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 1);
        expectedMap.put(VEGAN, 0);

        List<Diet> dietList = new ArrayList<>(Collections.singleton(VEGE));
        Map<Diet, Integer> cover = DietCalculator.countCover(dietList);

        assertThat(cover).isEqualTo(expectedMap);
    }

    @Test
    public void listNotEmptyWithNoVegetarianCount0(){
        List<Diet> dietList = new ArrayList<>(Arrays.asList(OMNI, VEGAN));
        Map<Diet, Integer> cover = DietCalculator.countCover(dietList);
        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 0);
        expectedMap.put(VEGAN, 1);

        assertThat(cover).isEqualTo(expectedMap);
    }

    @Test
    public void listWithTwoVegeAndOtherDietCount2() {
        List<Diet> dietList = new ArrayList<>(Arrays.asList(VEGE, OMNI, VEGE));

        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 2);
        expectedMap.put(VEGAN, 0);
        Map<Diet, Integer> cover = DietCalculator.countCover(dietList);

        assertThat(cover).isEqualTo(expectedMap);
    }
}
