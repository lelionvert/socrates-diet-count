package scenario;

import com.lcdlv.Cover;
import org.junit.jupiter.api.Test;

import static com.lcdlv.Diet.VEGAN;
import static com.lcdlv.Diet.VEGE;
import static org.assertj.core.api.Assertions.assertThat;
import static scenario.Meal.*;
import static scenario.Meal.THURSDAY_EVENING;

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

    @Test
    public void oneVegeCoverDifferentFromAnotherCoverWithDifferentCountOfMeals(){
        Cover vegeCover = new Cover(VEGE,2,1, THURSDAY_EVENING);
        Cover anotherVegeCover = new Cover(VEGE,3,0, FRIDAY_EVENING);

        assertThat(vegeCover).isNotEqualTo(anotherVegeCover);
    }

}
