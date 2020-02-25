/**
 * class Event
 * A standard, single event used in our calendar.
 *
 * @author: Alex Tobias
 */

import java.time.*;

public class Event {

    /**
     * Member variables
     * IMPORTANT: in the current implementation, we don't store an end time.
     * We store a start time and a duration.
     */
    private String name;
    private LocalDateTime startDateTime;
    private Duration duration;

    public Event(String name, LocalDateTime startDateTime, Duration duration) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.duration = duration;
    }

    /**
     * I don't expect toString() to be used in production
     * It's here for debugging purposes.
     */
    public String toString() {
        return String.format("E: %s @ %s, DUR: %s", name, startDateTime.toString(), duration.toString());
    }

    public String getName() {
        return name;
    }


    /**
     * We allow getting the start DateTime, start Date and start Time
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDate getStartDate() {
        return startDateTime.toLocalDate();
    }

    public LocalTime getStartTime() {
        return startDateTime.toLocalTime();
    }


    /**
     * End DateTime is calculated from startDateTime and duration.
     *
     * We allow getting the end DateTime, end Date and end Time.
     */
    public LocalDateTime getEndDateTime() {
        return startDateTime.plus(duration);
    }

    public LocalDate getEndDate() {
        return getEndDateTime().toLocalDate();
    }

    public LocalTime getEndTime() {
        return getEndDateTime().toLocalTime();
    }


    // The following code was used to debug and test the class. Please do not use this in production.
    /*
    public static void main(String[] args)
    {
        Event e1 = new Event("Event 1", LocalDateTime.of(2020, Month.FEBRUARY, 26, 13, 45), Duration.ofHours(30));
        System.out.println(e1);
        System.out.println(e1.getStartDate().toString());
        System.out.println(e1.getStartTime().toString());
        System.out.println(e1.getStartDateTime().toString());
    }
     */
}
