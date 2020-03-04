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

    private Memo memo = null;

    private int id;
    private static int numEvents = 0;

    private ArrayList<String> tags;
    private ArrayList<Alert> alerts;


    public Event(String name, LocalDateTime startDateTime, Duration duration) {
        numEvents++;
        id = numEvents;
        this.name = name;
        this.startDateTime = startDateTime;
        this.duration = duration;
    }

    public Event(String name, LocalDateTime startDateTime, Duration duration, Memo memo) {
        numEvents++;
        id = numEvents;
        this.name = name;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.memo = memo;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public int addMemo(Memo newMemo) {
        if(this.memo == null){
            this.memo = newMemo;
            newMemo.addEvent(this);
            return 1; //SUCCESS
        } else {
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
