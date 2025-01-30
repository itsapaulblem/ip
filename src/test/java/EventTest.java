package Alden;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void createEvent_normalDateTime_success() {
        Event event = new Event("Team Meeting", "2024/01/01 1400", "2024/01/01 1500");
        String expected = "[E][ ] Team Meeting (from: Jan 01 2024 2:00 PM to: Jan 01 2024 3:00 PM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void createEvent_dateOnly_setsToMidnight() {
        Event event = new Event("Team Meeting", "2024/01/01", "2024/01/02");
        String expected = "[E][ ] Team Meeting (from: Jan 01 2024 12:00 AM to: Jan 02 2024 12:00 AM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void toFileFormat_normalEvent_correctFormat() {
        Event event = new Event("Team Meeting", "2024/01/01 1400", "2024/01/01 1500");
        String expected = "E | 0 | Team Meeting | 2024/01/01 1400 | 2024/01/01 1500";
        assertEquals(expected, event.toFileFormat());
    }

    @Test
    public void createEvent_invalidDateFormat_defaultsToCurrentTime() {
        Event event = new Event("Team Meeting", "invalid_date", "invalid_date");
        // The exact time will vary, but the string should contain the current date
        assertTrue(event.toString().contains(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
        ));
    }
}
