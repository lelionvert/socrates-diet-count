package scenario;

import com.lcdlv.AttendeeParser;
import com.lcdlv.ColdMealCalculator;
import com.lcdlv.Diet;
import com.lcdlv.DietCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.CheckedInputStream;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AttendeesWithMockitoTest {

    @InjectMocks
    DietCalculator dietCalculator;

    @Mock
    AttendeeParser attendeeParser;

    @Mock
    private ColdMealCalculator coldMealCalculator;

    @Test
    public void attendeesTestWithMockito() {

        String input = "";
        dietCalculator.countCoversOfAttendeesWithParser(input);
        Mockito.verify(attendeeParser).parseAttendees(input);
    }

    @Test
    public void coldMealTestWithMockito(){
        String input = "";
        Mockito.when(attendeeParser.parseAttendees(input)).thenReturn(Collections.singletonList(
                new Attendee(Diet.VEGE, DayOfWeek.THURSDAY, LocalTime.of(20,0)))
        );
        dietCalculator.countCoversOfAttendeesWithParser(input);
        Mockito.verify(coldMealCalculator).isCold(any());
    }

    @Test
    public void aNewTest(){

        String input = "";
        List<Attendee> attendees = Arrays.asList(
                new Attendee(Diet.VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)),
                new Attendee(Diet.VEGE, DayOfWeek.THURSDAY, LocalTime.of(20, 0)));
        Mockito.when(attendeeParser.parseAttendees(input)).thenReturn(attendees);
        dietCalculator.countCoversOfAttendeesWithParser(input);
        Mockito.verify(coldMealCalculator, Mockito.times(attendees.size())).isCold(any());
    }
}
