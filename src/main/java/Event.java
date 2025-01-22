import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return "[E][" + getStatusIcon() + "] " + description +
                " (from: " + from.format(formatter) +
                " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description +
                " | " + from.format(formatter) +
                " | " + to.format(formatter);
    }
}