package scenario;

import com.lcdlv.Diet;
import com.lcdlv.DietCalculator;
import org.junit.jupiter.api.Disabled;
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
    public void emptyListGivesMapWithEveryDietCount0() {
        List<Diet> list = new ArrayList<>();
        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE,0);
        expectedMap.put(VEGAN, 0);
        expectedMap.put(OMNI, 0);
        expectedMap.put(PESCE,0);

        Map<Diet, Integer> cover = DietCalculator.countCover(list);

        assertThat(cover).isEqualTo(expectedMap);
    }

    @Disabled
    @Test
    public void listWithOneVegetarianCount1(){
        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 1);
        expectedMap.put(VEGAN, 0);


        List<Diet> dietList = new ArrayList<>(Collections.singleton(VEGE));
        Map<Diet, Integer> cover = DietCalculator.countCover(dietList);

        assertThat(cover).isEqualTo(expectedMap);
    }
}
