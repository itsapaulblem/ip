package Alden;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. This class extends the {@link Task} class and adds functionality
 * to handle a specific deadline date and time associated with the task.
 */
public class Deadline extends Task {
    private final LocalDateTime dateTime; // The deadline date and time for the task

    /**
     * Constructs a new Deadline task with the specified description and deadline time.
     * The deadline time is parsed from the given string using {@link #parseDateTime(String)}.
     *
     * @param description A brief description of the task.
     * @param by The deadline time as a string,
     *     which can be a specific date or special keywords (e.g., "today", "tomorrow").
     */
    public Deadline(String description, String by) throws AldenException {
        super(description);
        this.dateTime = parseDateTime(by);
    }

    /**
     * Parses the deadline string to determine the date and time of the deadline.
     * It handles specific keywords like "today" and "tomorrow",
     * and also parses custom date-time strings.
     *
     * @param by A string representing the deadline time.
     *     Can be a specific date (yyyy/MM/dd) or a date-time (yyyy/MM/dd HHmm).
     * @return A {@link LocalDateTime} object representing the parsed deadline date and time.
     */
    private LocalDateTime parseDateTime(String by) throws AldenException {
        // Handle special keywords
        if (by.equalsIgnoreCase("today")) {
            return LocalDateTime.now();
        } else if (by.equalsIgnoreCase("tomorrow")) {
            return LocalDateTime.now().plusDays(1);
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
            throw new AldenException("Error: Invalid date format. Use yyyy/MM/dd or yyyy/MM/dd HHmm");
        }
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     * The format includes the task type (D), status, description, and the formatted deadline time.
     *
     * @return A string representation of the task for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dateTime.format(formatter);
    }

    /**
     * Returns a string representation of the task to be displayed in the user interface.
     * The format includes the task type (D), status, description, and the formatted deadline time.
     *
     * @return A string representation of the task for display purposes.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon()
                + "] " + description + " (by: "
                +
                dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")";
    }
}
