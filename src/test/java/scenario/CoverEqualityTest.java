package scenario;

import com.lcdlv.Cover;
import org.junit.jupiter.api.Test;

import static com.lcdlv.Diet.VEGAN;
import static com.lcdlv.Diet.VEGE;
import static org.assertj.core.api.Assertions.assertThat;
import static scenario.Meals.THURSDAY_EVENING;

public class CoverEqualityTest {

    @Test
    public void oneVegeCoverWithNoMealIsEqualToAnotherVegeCoverWithNoMealOnThursdayEvening() {
        Cover vegeCover = new Cover(VEGE, 0, 0, THURSDAY_EVENING);
        Cover anotherVegeCover = new Cover(VEGE, 0, 0, THURSDAY_EVENING);

        assertThat(vegeCover).isEqualTo(anotherVegeCover);
    }

    @Test
    public void oneVegeCoverWithNoMealIsDifferentFromAnotherCoverWithADifferentDiet(){
        Cover vegeCover = new Cover(VEGE,0,0, THURSDAY_EVENING);
        Cover veganCover = new Cover(VEGAN,0,0, THURSDAY_EVENING);

        assertThat(vegeCover).isNotEqualTo(veganCover);
    }
}
