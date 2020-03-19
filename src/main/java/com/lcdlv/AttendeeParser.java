package com.lcdlv;

import scenario.Attendee;

import java.util.List;

public interface AttendeeParser {

    List<Attendee> parseAttendees(String input);
}
