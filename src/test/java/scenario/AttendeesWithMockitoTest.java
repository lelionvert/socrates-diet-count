package scenario;

import com.lcdlv.AttendeeParser;
import com.lcdlv.DietCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AttendeesWithMockitoTest {

    @InjectMocks
    DietCalculator dietCalculator;

    @Mock
    AttendeeParser attendeeParser;

    @Test
    public void attendeesTestWithMockito() {

        String input = "";
        dietCalculator.countCoversOfAttendeesWithParser(input);
        Mockito.verify(attendeeParser).parseAttendees(input);
    }
}
