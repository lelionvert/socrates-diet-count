package scenario;

import com.lcdlv.Diet;
import com.lcdlv.DietCalculator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}
