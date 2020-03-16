package scenario;

import com.lcdlv.Diet;
import com.lcdlv.DietCalculator;
import org.junit.jupiter.api.Test;

import java.util.*;

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

        assertThat(cover.get(Diet.VEGGE)).isEqualTo(0);
    }

    @Test
    public void listWithOneVegetarianCount1(){
        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(Diet.VEGGE, 1);

        List<Diet> dietList = new ArrayList<>(Collections.singleton(Diet.VEGGE));
        Map<Diet, Integer> cover = DietCalculator.countCover(dietList);

        assertThat(cover).isEqualTo(expectedMap);
    }
}
