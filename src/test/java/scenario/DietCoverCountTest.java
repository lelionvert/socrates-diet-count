package scenario;

import com.lcdlv.*;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
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

        Map<Diet, Integer> covers = new DietCalculator().countCoversByDiet(diets);

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

        Map<Diet, Integer> covers = new DietCalculator().countCoversByDiet(diets);

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

        Map<Diet, Integer> covers = new DietCalculator().countCoversByDiet(diets);

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

        Map<Diet, Integer> covers = new DietCalculator().countCoversByDiet(diets);

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

        Map<Diet, Integer> covers = new DietCalculator().countCoversByDiet(diets);

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

        Map<Diet, Integer> covers = new DietCalculator().countCoversByDiet(diets);

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

        Map<Diet, Integer> covers = new DietCalculator().countCoversByDiet(diets);

        assertThat(covers).isEqualTo(expectedCovers);
    }


    // ok [{}], [] ===> [{}]

    // ok [{vege , jeudi, 20H}] , [Vege] === > [{vege, 1, 0, Jeudi soir}]
    // [{vege , jeudi, 20H}, {vegan , jeudi, 20H}] ===>  [{vege, 1, 0, jeudi soir}, {vegan, 1, 0, jeudi soir}]

    //[{vege , jeudi, 22H}}] ===> [{vege, 1, 1, jeudi soir}]
    // [{vege , jeudi, 22H}, {vegan , jeudi, 22H}] ===>  [{vege, 1, 1, jeudi soir}, {vegan, 1, 1, jeudi soir}]

    // [{vege , jeudi, 20H}, {vege , jeudi, 22H}] ===>  [{vege, 1, 1, jeudi soir}]
    // [{vegan , jeudi, 20H}, {vege , jeudi, 22H}] ===>  [{vegan, 1, 0, jeudi soir}, {vege, 1, 1, jeudi soir}]

    @Test
    public void returnsZeroCoverWhenNoAttendees() {
        AttendeeParserDouble parser = new AttendeeParserDouble();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        List<Attendee> attendees = Collections.emptyList();
        List<Cover> expectedCovers = Arrays.asList(
                new Cover(VEGE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(OMNI, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(PESCE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(VEGAN, 0, 0, Meal.THURSDAY_EVENING)
        );

        List<Cover> covers = dietCalculator.countCoversOfAttendees(attendees);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsVegeHotCoverWhenHavingOneVegeAttendeeOnThursdayAt20() {
        List<Cover> expectedCovers = Arrays.asList(
                new Cover(VEGE, 1, 0, Meal.THURSDAY_EVENING),
                new Cover(OMNI, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(PESCE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(VEGAN, 0, 0, Meal.THURSDAY_EVENING)
        );

        AttendeeParserDouble parser = new AttendeeParserDouble();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        List<Attendee> attendees = Collections.singletonList(
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)));

        List<Cover> covers = dietCalculator.countCoversOfAttendees(attendees);
        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsTwoCoversVegeWhenHavingTwoVegeAttendeesArrivesThursdayAt20() {
        AttendeeParserDouble parser = new AttendeeParserDouble();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        List<Attendee> attendees = Arrays.asList(
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)),
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)));

        List<Cover> expectedCovers = Arrays.asList(
                new Cover(VEGE, 2, 0, Meal.THURSDAY_EVENING),
                new Cover(OMNI, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(PESCE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(VEGAN, 0, 0, Meal.THURSDAY_EVENING)
        );

        List<Cover> covers = dietCalculator.countCoversOfAttendees(attendees);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnThreeVegeCoversWhenHavingThreeVegeAttendeesOnThursdayAt20() {
        AttendeeParserDouble parser = new AttendeeParserDouble();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        List<Attendee> attendees = Arrays.asList(
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)),
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)),
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0))
        );
        List<Cover> expectedCovers = Arrays.asList(
                new Cover(VEGE, 3, 0, Meal.THURSDAY_EVENING),
                new Cover(OMNI, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(PESCE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(VEGAN, 0, 0, Meal.THURSDAY_EVENING)
        );

        List<Cover> covers = dietCalculator.countCoversOfAttendees(attendees);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsOneVeganCoverWhenHavingOneVeganAttendee() {
        AttendeeParserDouble parser = new AttendeeParserDouble();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        List<Attendee> attendees = Collections.singletonList(
                new Attendee(VEGAN, DayOfWeek.THURSDAY, LocalTime.of(20, 0))
        );
        List<Cover> expectedCovers = Arrays.asList(
                new Cover(VEGE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(OMNI, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(PESCE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(VEGAN, 1, 0, Meal.THURSDAY_EVENING)
        );

        List<Cover> covers = dietCalculator.countCoversOfAttendees(attendees);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnOnlyOneVeganCoverWhenHavingThreeSuggestedDietsAndOneVeganAttendee() {
        AttendeeParserDouble parser = new AttendeeParserDouble();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        List<Attendee> attendees = Collections.singletonList(
                new Attendee(VEGAN, DayOfWeek.THURSDAY, LocalTime.of(20, 0))
        );
        List<Cover> expectedCovers = Arrays.asList(
                new Cover(VEGE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(OMNI, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(PESCE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(VEGAN, 1, 0, Meal.THURSDAY_EVENING)
        );

        List<Cover> covers = dietCalculator.countCoversOfAttendees(attendees);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsOnlyOneOmniCoverWhenHavingThreeSuggestedDietsAndOneOmniAttendee() {
        AttendeeParserDouble parser = new AttendeeParserDouble();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        List<Attendee> attendees = Collections.singletonList(
                new Attendee(OMNI, DayOfWeek.THURSDAY, LocalTime.of(20, 0))
        );
        List<Cover> expectedCovers = Arrays.asList(
                new Cover(VEGE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(OMNI, 1, 0, Meal.THURSDAY_EVENING),
                new Cover(PESCE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(VEGAN, 0, 0, Meal.THURSDAY_EVENING)
        );

        List<Cover> covers = dietCalculator.countCoversOfAttendees(attendees);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void returnsOnlyOneVegeAndOneOmniCoverWhenHavingTwoSuggestedDietsAndVegeAndOmniAttendees() {
        AttendeeParserDouble parser = new AttendeeParserDouble();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        List<Attendee> attendees = Arrays.asList(
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)),
                new Attendee(OMNI, DayOfWeek.THURSDAY, LocalTime.of(20, 0))
        );
        List<Cover> expectedCovers = Arrays.asList(
                new Cover(VEGE, 1, 0, Meal.THURSDAY_EVENING),
                new Cover(OMNI, 1, 0, Meal.THURSDAY_EVENING),
                new Cover(PESCE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(VEGAN, 0, 0, Meal.THURSDAY_EVENING)
        );

        List<Cover> covers = dietCalculator.countCoversOfAttendees(attendees);

        assertThat(covers).isEqualTo(expectedCovers);
    }

    @Test
    public void parserIsCalledWhenWeCountCovers() {
        AttendeeParserDouble parser = new AttendeeParserDouble();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        String input = "";
        List<Cover> covers = dietCalculator.countCoversOfAttendeesWithParser(input);

        assertThat(parser.isCalled()).isTrue();
    }

    @Test
    public void parserDataAreNotCorruptedWhenWeCountCovers() {
        AttendeeParserDoubleWithInput parser = new AttendeeParserDoubleWithInput();
        ColdMealCalculatorDouble coldMealCalculator = new ColdMealCalculatorDouble();
        DietCalculator dietCalculator = new DietCalculator(parser,coldMealCalculator);
        String input = "";
        List<Cover> covers = dietCalculator.countCoversOfAttendeesWithParser(input);

        assertThat(parser.isCalledWith(input)).isTrue();
    }

}
