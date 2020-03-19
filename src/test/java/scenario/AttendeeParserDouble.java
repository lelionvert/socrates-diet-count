package scenario;

import com.lcdlv.AttendeeParser;

import java.util.Collections;
import java.util.List;

public class AttendeeParserDouble implements AttendeeParser {

    private boolean called;

    public boolean isCalled() {
        return called;
    }

    @Override
    public List<Attendee> parseAttendees(String input) {
        called = true;
        return Collections.emptyList();
    }
}
