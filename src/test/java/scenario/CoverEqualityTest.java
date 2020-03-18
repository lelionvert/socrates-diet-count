package scenario;

import com.lcdlv.Cover;
import org.junit.jupiter.api.Test;

import static com.lcdlv.Diet.VEGE;
import static org.assertj.core.api.Assertions.assertThat;
import static scenario.Meals.THURSDAY_EVENING;

public class CoverEqualityTest {

    @Test
    public void oneEmptyCoverIsEqualToAnotherEmptyCover() {
        Cover emptyCover = new Cover(VEGE, 0, 0, THURSDAY_EVENING);
        Cover anotherEmptyCover = new Cover(VEGE, 0, 0, THURSDAY_EVENING);

        assertThat(emptyCover).isEqualTo(anotherEmptyCover);
    }
}
