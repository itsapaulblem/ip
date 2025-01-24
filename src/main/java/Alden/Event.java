package Alden;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end time.
 * The event is stored with its description and a time range (from and to).
 * This class extends the {@link Task} class and provides functionality
 * to parse, display, and save event-related information.
 */
public class Event extends Task {
    private final LocalDateTime from; // Start time of the event
    private final LocalDateTime to;   // End time of the event

    /**
     * Constructs an Event object with the given description and date-time range.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Parses the given date-time string and returns a {@link LocalDateTime} object.
     * The method tries to parse the date-time using both date and time. If only a date is provided,
     * it defaults to the start of the day (00:00).
     *
     * @param dateTimeString The date-time string to be parsed.
     * @return A {@link LocalDateTime} representing the parsed date and time.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        try {
            // Try to parse with both date and time
            if (dateTimeString.contains(" ")) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
                return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
            } else {
                // If only date is provided, set time to start of day (00:00)
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate date = LocalDate.parse(dateTimeString, dateFormatter);
                return date.atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Use yyyy/MM/dd or yyyy/MM/dd HHmm");
            return LocalDateTime.now(); // Default to current time if parsing fails
        }
    }

    /**
     * Returns the string representation of the event task.
     * This includes the task type, status icon, description, and the start and end times of the event.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return "[E][" + getStatusIcon() + "] " + description +
                " (from: " + from.format(formatter) +
                " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the event task in a format suitable for saving to a file.
     * This includes the task type, status, description, start time, and end time.
     *
     * @return A string representing the event task in a file-friendly format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description +
                " | " + from.format(formatter) +
                " | " + to.format(formatter);
    }
}
