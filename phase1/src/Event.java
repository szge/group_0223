import java.time.*;
import java.util.ArrayList;

public class Event {

    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Duration duration;

    private Memo memo = null;

    private int id;
    private static int numEvents = 0;

    private ArrayList<String> tags;
    private ArrayList<Alert> alerts;

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        numEvents++;
        id = numEvents;
        this.name = name;
        startDateTime = start;
        endDateTime = end;
        this.duration = Duration.between(start, end);
    }

    public Event(String name, LocalDateTime start, LocalDateTime end, Memo memo) {
        numEvents++;
        id = numEvents;
        this.name = name;
        startDateTime = start;
        endDateTime = end;
        duration = Duration.between(start, end);
        this.memo = memo;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public int addMemo(Memo newMemo) {
        if (memo == null) {
            memo = newMemo;
            newMemo.addEvent(id);
            return 1; //SUCCESS
        } else {
            return -1; //FAILURE
        }
    }

    // currently this supports an event having two alerts at the same time
    // an event can have multiple individual alerts as well as frequent alerts
    public int addAlert(Alert newAlert) {
        if (newAlert.getLocalDateTime().isAfter(endDateTime)) {
            return -1; // FAILURE
        } else {
            alerts.add(newAlert);
            return 1; //SUCCESS
        }
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return String.format("E# %d %s @ %s until %s", id, name, startDateTime.toString(), endDateTime.toString());
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

}
