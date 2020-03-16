package scenario;

import com.lcdlv.Diet;
import com.lcdlv.DietCalculator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.lcdlv.Diet.VEGAN;
import static com.lcdlv.Diet.VEGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class VeganCountTest {

    @Test
    public void listWithNoVeganAndTwoVegeCountTwoVegeandZeroVegan(){
        List<Diet> dietList = new ArrayList<>(Arrays.asList(VEGE,VEGE));
        Map<Diet, Integer> cover = DietCalculator.countCover(dietList);
        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(Diet.VEGE,2);
        expectedMap.put(VEGAN, 0);
        assertThat(cover).isEqualTo(expectedMap);
    }

    @Test
    public void listWithOneVeganAndTwoVegeCountOneVeganAndTwoVege(){
        List<Diet> dietList = new ArrayList<>(Arrays.asList(VEGE, VEGAN, VEGE));

        Map<Diet, Integer> cover = DietCalculator.countCover(dietList);
        Map<Diet, Integer> expectedMap = new HashMap<>();
        expectedMap.put(VEGE, 2);
        expectedMap.put(VEGAN, 1);

        assertThat(cover).isEqualTo(expectedMap);
    }
}