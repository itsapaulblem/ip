package Alden;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void createDeadline_normalDateTime_success() {
        Deadline deadline = new Deadline("Submit Report", "2024/01/01 1430");
        String expected = "[D][ ] Submit Report (by: Jan 01 2024 2:30 PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void createDeadline_dateOnly_setsToMidnight() {
        Deadline deadline = new Deadline("Submit Report", "2024/01/01");
        String expected = "[D][ ] Submit Report (by: Jan 01 2024 12:00 AM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void createDeadline_today_setsToCurrentDate() {
        Deadline deadline = new Deadline("Submit Report", "today");
        LocalDateTime now = LocalDateTime.now();
        assertTrue(deadline.toString().contains(now.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
    }

    @Test
    public void toFileFormat_normalDeadline_correctFormat() {
        Deadline deadline = new Deadline("Submit Report", "2024/01/01 1430");
        String expected = "D | 0 | Submit Report | 2024/01/01 1430";
        assertEquals(expected, deadline.toFileFormat());
    }
}
