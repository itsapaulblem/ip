package Alden;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        this.dateTime = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) {
        // Handle special keywords
        if (by.equalsIgnoreCase("today")) {
            return LocalDateTime.now();
        } else if (by.equalsIgnoreCase("tomorrow")) {
            return LocalDateTime.now().plus(1, ChronoUnit.DAYS);
        }

        try {
            // First try parsing as date-time format
            if (by.contains(" ")) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
                return LocalDateTime.parse(by, dateTimeFormatter);
            } else {
                // If only date is provided, set time to midnight (00:00)
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate date = LocalDate.parse(by, dateFormatter);
                return date.atStartOfDay();
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid date format. Use yyyy/MM/dd or yyyy/MM/dd HHmm");
            return LocalDateTime.now(); // Default to current time if parsing fails
        }
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " +
                dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")";
    }
}