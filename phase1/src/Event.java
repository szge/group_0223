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

    public Duration getDuration() {
        return duration;
    }


    //Arsham's code
    //Romove the tag with content
    public void removeTag(String content){
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).equals(content)){
                tags.remove(i);
            }
        }
    }

    //returns memo from this event
    public Memo getMemo(){
        return this.memo;
    }

    //edit the name for event
    public void editName(String name){
        this.name = name;
    }

    //Change the memo for this event
    public void editMemo(Memo memo){
        this.memo = memo;
    }

    //change the start of the event
    public void editStart(LocalDateTime start){
        if (start.isBefore(this.endDateTime)){
            this.startDateTime = start;
        }
    }

    //change the end of this event
    public void editEnd(LocalDateTime end){
        if (end.isAfter(this.startDateTime)){
            this.endDateTime = end;
        }
    }

    //remove the memo
    public void removeMemo(){
        this.memo = null;
    }

    //remove the alert
    public void removeAlert(Alert alert){
        this.alerts.remove(alert);
    }
}
