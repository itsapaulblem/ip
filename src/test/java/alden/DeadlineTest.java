package alden;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void createDeadline_normalDateTime_success() throws AldenException {
        Deadline deadline = new Deadline("Submit Report", "2025/12/31 1430");
        String expected = "[D][ ] Submit Report (by: Dec 31 2025 2:30 PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void createDeadline_dateOnly_setsToMidnight() throws AldenException {
        Deadline deadline = new Deadline("Submit Report", "2025/12/31");
        String expected = "[D][ ] Submit Report (by: Dec 31 2025 12:00 AM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void createDeadline_today_setsToCurrentDate() throws AldenException {
        Deadline deadline = new Deadline("Submit Report", "today");
        LocalDateTime now = LocalDateTime.now();
        assertTrue(deadline.toString().contains(now.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
    }

    @Test
    public void toFileFormat_normalDeadline_correctFormat() throws AldenException {
        Deadline deadline = new Deadline("Submit Report", "2025/12/31 1430");
        String expected = "D | 0 | Submit Report | 2025/12/31 1430";
        assertEquals(expected, deadline.toFileFormat());
    }
}
