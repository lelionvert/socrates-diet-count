package scenario;

import com.lcdlv.AttendeeParser;
import com.lcdlv.ColdMealCalculator;
import com.lcdlv.Cover;
import com.lcdlv.DietCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.lcdlv.Diet.*;
import static com.lcdlv.Diet.VEGAN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AttendeesWithMockitoTest {

    @InjectMocks
    DietCalculator dietCalculator;

    @Mock
    AttendeeParser attendeeParser;

    @Mock
    private ColdMealCalculator coldMealCalculator;

    @Test
    public void dataIsParsedWhenWeCountCovers() {

        String input = "";
        dietCalculator.countCoversOfAttendeesWithParser(input);
        verify(attendeeParser).parseAttendees(input);
    }

    @Test
    public void checkForEachAttendeeIfHisOrHerMealIsColdWhenCoversAreCounted() {

        String input = "";
        List<Attendee> attendees = Arrays.asList(
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)),
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)));
        when(attendeeParser.parseAttendees(input)).thenReturn(attendees);
        dietCalculator.countCoversOfAttendeesWithParser(input);
        verify(coldMealCalculator, times(attendees.size())).isCold(any());
    }

    @Test
    public void returnOneColdMealWhenArrivalTimeOfAnAttendeeIs22hOnThursday() {
        String input = "";
        List<Cover> expectedCovers = Arrays.asList(
                new Cover(VEGE, 1, 1, Meal.THURSDAY_EVENING),
                new Cover(OMNI, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(PESCE, 0, 0, Meal.THURSDAY_EVENING),
                new Cover(VEGAN, 0, 0, Meal.THURSDAY_EVENING)
        );

        List<Attendee> attendees = Collections.singletonList(
                new Attendee(VEGE, DayOfWeek.THURSDAY, LocalTime.of(22, 0))
        );
        when(attendeeParser.parseAttendees(input)).thenReturn(attendees);
        when(coldMealCalculator.isCold(any())).thenReturn(true);
        List<Cover> covers = dietCalculator.countCoversOfAttendeesWithParser(input);
        assertThat(covers).isEqualTo(expectedCovers);
    }
}
