package scenario;

import com.lcdlv.Cover;
import com.lcdlv.Diet;
import com.lcdlv.DietCalculator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.lcdlv.Diet.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DietCoverCountTest {

    @Test
    public void returnsAllCoversByDietEqualsZeroWhenNoDietGiven() {
        List<Diet> diets = new ArrayList<>();
        Map<Diet, Integer> expectedCovers = new HashMap<>();
        expectedCovers.put(VEGE, 0);
        expectedCovers.put(VEGAN, 0);
        expectedCovers.put(OMNI, 0);
        expectedCovers.put(PESCE, 0);

        Diet[] suggestedDiets = new Diet[]{VEGE, VEGAN, OMNI, PESCE};
        Map<Diet, Integer> covers = new DietCalculator(suggestedDiets).countCoversByDiet(diets);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsOnlyOneVegeCoverWhenGivingOneVegeDiet() {
        List<Diet> diets = new ArrayList<>(Collections.singletonList(VEGE));

        Map<Diet, Integer> expectedCovers = new HashMap<>();
        expectedCovers.put(VEGE, 1);
        expectedCovers.put(VEGAN, 0);
        expectedCovers.put(OMNI, 0);
        expectedCovers.put(PESCE, 0);

        Diet[] suggestedDiets = new Diet[]{VEGE, VEGAN, OMNI, PESCE};
        Map<Diet, Integer> covers = new DietCalculator(suggestedDiets).countCoversByDiet(diets);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsOnlyTwoVegeCoversWhenGivingTwoVegeDiets() {
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, VEGE));

        Map<Diet, Integer> expectedCovers = new HashMap<>();
        expectedCovers.put(VEGE, 2);
        expectedCovers.put(VEGAN, 0);
        expectedCovers.put(OMNI, 0);
        expectedCovers.put(PESCE, 0);

        Diet[] suggestedDiets = new Diet[]{VEGE, VEGAN, OMNI, PESCE};
        Map<Diet, Integer> covers = new DietCalculator(suggestedDiets).countCoversByDiet(diets);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsOnlyTwoVegeAndOneVeganCoversWhenTwoVegeAndOneVeganDietsAreGiven() {
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, VEGAN, VEGE));

        Map<Diet, Integer> expectedCovers = new HashMap<>();
        expectedCovers.put(VEGE, 2);
        expectedCovers.put(VEGAN, 1);
        expectedCovers.put(OMNI, 0);
        expectedCovers.put(PESCE, 0);

        Diet[] suggestedDiets = new Diet[]{VEGE, VEGAN, OMNI, PESCE};
        Map<Diet, Integer> covers = new DietCalculator(suggestedDiets).countCoversByDiet(diets);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsOnlyThreeVegeAndTwoVeganCoversWhenThreeVegeAndTwoVeganDietsAreGiven() {
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, VEGAN, VEGE, VEGAN, VEGE));

        Map<Diet, Integer> expectedCovers = new HashMap<>();
        expectedCovers.put(VEGE, 3);
        expectedCovers.put(VEGAN, 2);
        expectedCovers.put(OMNI, 0);
        expectedCovers.put(PESCE, 0);

        Diet[] suggestedDiets = new Diet[]{VEGE, VEGAN, OMNI, PESCE};
        Map<Diet, Integer> covers = new DietCalculator(suggestedDiets).countCoversByDiet(diets);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsOnlyOneVegeTwoVeganTwoOmniCoversWhenOneVegeTwoVeganTwoOmniDietsAreGiven() {
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, VEGAN, OMNI, VEGAN, OMNI));

        Map<Diet, Integer> expectedCovers = new HashMap<>();
        expectedCovers.put(VEGE, 1);
        expectedCovers.put(VEGAN, 2);
        expectedCovers.put(OMNI, 2);
        expectedCovers.put(PESCE, 0);

        Diet[] suggestedDiets = new Diet[]{VEGE, VEGAN, OMNI, PESCE};
        Map<Diet, Integer> covers = new DietCalculator(suggestedDiets).countCoversByDiet(diets);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsOnlyOneVegeTwoOmniTwoPesceWhenOneVegeTwoOmniTwoPesceDietsAreGiven() {
        List<Diet> diets = new ArrayList<>(Arrays.asList(VEGE, OMNI, OMNI, PESCE, PESCE));

        Map<Diet, Integer> expectedCovers = new HashMap<>();
        expectedCovers.put(VEGE, 1);
        expectedCovers.put(VEGAN, 0);
        expectedCovers.put(OMNI, 2);
        expectedCovers.put(PESCE, 2);

        Diet[] suggestedDiets = new Diet[]{VEGE, VEGAN, OMNI, PESCE};
        Map<Diet, Integer> covers = new DietCalculator(suggestedDiets).countCoversByDiet(diets);

        assertThat(covers).isEqualTo(expectedCovers);
    }


    // [{}], [] ===> [{}]

    // [{vege , jeudi, 20H}] === > [{vege, 1, 0, Jeudi soir}]
    // [{vege , jeudi, 20H}, {vegan , jeudi, 20H}] ===>  [{vege, 1, 0, jeudi soir}, {vegan, 1, 0, jeudi soir}]

    //[{vege , jeudi, 22H}}] ===> [{vege, 1, 1, jeudi soir}]
    // [{vege , jeudi, 22H}, {vegan , jeudi, 22H}] ===>  [{vege, 1, 1, jeudi soir}, {vegan, 1, 1, jeudi soir}]

    // [{vege , jeudi, 20H}, {vege , jeudi, 22H}] ===>  [{vege, 1, 1, jeudi soir}]
    // [{vegan , jeudi, 20H}, {vege , jeudi, 22H}] ===>  [{vegan, 1, 0, jeudi soir}, {vege, 1, 1, jeudi soir}]

    @Test
    public void returnZeroCoverWhenNoAttendees(){

        DietCalculator dietCalculator = new DietCalculator(new Diet[]{});
        List<Attendee> attendees = Collections.emptyList() ;
        List<Cover> expectedCovers = Collections.emptyList();

        List<Cover> covers = dietCalculator.countCoversOfAttendees(attendees);

        assertThat(covers).isEqualTo(expectedCovers);
    }



}
