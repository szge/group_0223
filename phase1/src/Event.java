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

    public String toString() {
        return String.format("E# %d %s @ %s, DUR: %s", id, name, startDateTime.toString(), duration.toString());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDate getStartDate() {
        return startDateTime.toLocalDate();
    }

    public LocalTime getStartTime() {
        return startDateTime.toLocalTime();
    }

    public LocalDateTime getEndDateTime() {
        return startDateTime.plus(duration);
    }

    public LocalDate getEndDate() {
        return getEndDateTime().toLocalDate();
    }

    public LocalTime getEndTime() {
        return getEndDateTime().toLocalTime();
    }

}
