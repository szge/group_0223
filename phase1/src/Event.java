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

    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<Alert> alerts = new ArrayList<>();

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        numEvents++;
        id = numEvents;
        this.name = name;
        startDateTime = start;
        endDateTime = end;
        duration = Duration.between(start, end);
    }

    public Event(String name, LocalDateTime start, LocalDateTime end, Memo memo) {
        numEvents++;
        id = numEvents;
        this.name = name;
        startDateTime = start;
        endDateTime = end;
        duration = Duration.between(start, end);
        addMemo(memo);
    }


     // add tag to this Events ArrayList of tags
    public void addTag(String tag) {
        tags.add(tag);
    }

   // add newMemo to this Event, if this Event is already associated with a Memo, update it
    public void addMemo(Memo newMemo) {
        if (memo == null) {
            memo = newMemo;
            newMemo.addEvent(id);
        } else {
            editMemo(newMemo);
        }
    }

    // currently this supports an event having two alerts at the same time
    // an event can have multiple individual alerts as well as frequent alerts
    public void addAlert(Alert newAlert) {
        if (!newAlert.getLocalDateTime().isAfter(endDateTime)) {
            alerts.add(newAlert);
        }
    }

    public static void bringDownNum() {
        this.numEvents --;
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

    // return the start date and time of this Event
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    // return the end date and time of this Event
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    // return the duration of this Event
    public Duration getDuration() {
        return duration;
    }

    // remove the tag with content
    public void removeTag(String content){
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).equals(content)){
                tags.remove(i);
            }
        }
    }

    // return the Memo associated with this Event
    public Memo getMemo(){
        return this.memo;
    }

    // change the name of this Event to newName
    public void editName(String newName) {
        name = newName;
    }

    // change the memo associated with this Event
    public void editMemo(Memo memo) {
        this.memo = memo;
    }

    // change the start time and date of this Event
    public void editStart(LocalDateTime start) {
        if (start.isBefore(endDateTime)){
            startDateTime = start;
        }
    }

    // change the end time and date of this event
    public void editEnd(LocalDateTime end){
        if (end.isAfter(this.startDateTime)){
            this.endDateTime = end;
        }
    }

    //remove the memo associated with this Event
    public void removeMemo(){
        this.memo = null;
    }

    // remove alert from this Events ArrayList of Alerts
    public void removeAlert(Alert alert){
        this.alerts.remove(alert);
    }
}
