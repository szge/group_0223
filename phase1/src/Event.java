/**
 * class Event
 * A standard, single event used in our calendar.
 *
 * @author: Alex Tobias
 */

import java.time.*;
import java.util.ArrayList;

public class Event {

    /**
     * Member variables
     * IMPORTANT: in the current implementation, we don't store an end time.
     * We store a start time and a duration.
     */
    private String name;
    private LocalDateTime startDateTime;
    private Duration duration;

    private int id;

    private ArrayList<String> tags;
    private ArrayList<Alert> alerts;

    private Memo memo = null;


    public Event() {
        this.id = 0;
        this.name = "";
        this.startDateTime = LocalDateTime.now();
        this.duration = Duration.ofHours(1);
    }

    public Event(int id, String name) {
        this.id = id;
        this.name = name;
        this.startDateTime = LocalDateTime.now();
        this.duration = Duration.ofHours(1);
    }


    public Event(int id, String name, LocalDateTime startDateTime, Duration duration) {
        this.id = id;
        this.name = name;
        this.startDateTime = startDateTime;
        this.duration = duration;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public int addMemo(Memo newMemo) {
        if(this.memo == null){
            this.memo = newMemo;
            newMemo.addEvent(this);
            return 1; //SUCCESS
        }
        else {
            return -1; //ERROR
        }

    }

    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * I don't expect toString() to be used in production
     * It's here for debugging purposes.
     */
    public String toString() {
        return String.format("E# %d %s @ %s, DUR: %s", id, name, startDateTime.toString(), duration.toString());
    }

    public int getId() {
        return id;
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
