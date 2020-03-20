package scenario;

import com.lcdlv.AttendeeParser;

import java.util.Collections;
import java.util.List;

public class AttendeeParserDoubleWithInput implements AttendeeParser {

    private String input;

    @Override
    public List<Attendee> parseAttendees(String input) {
        this.input = input;
        return Collections.emptyList();
    }

    public boolean isCalledWith(String input) {
        return input.equals(this.input);
    }
}
