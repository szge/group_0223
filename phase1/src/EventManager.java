import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.*;

public class EventManager {

    private ArrayList<Event> store;

    public EventManager() {
        this.store = new ArrayList<>();
    }

    //1)Basics for events

    public Event createEvent(String name, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(name, start, end);
        this.store.add(event);
        return event;
    }

    public void createEvent(String name, LocalDateTime start, LocalDateTime end, Memo memo) {
        Event event = new Event(name, start, end, memo);
        this.store.add(event);
    }

    public void deleteEvent(Event event) {
        this.store.remove(event);
    }

    public void editEventStart(Event event, LocalDateTime newStart) {
        event.editStart(newStart); //need a method for this event
    }

    public void editEventEnd(Event event, LocalDateTime newEnd) {
        event.editEnd(newEnd); //need a method for this event
    }

    public void editName(Event event, String newName) {
        event.editName(newName); //need a method for this event
    }


    //2)Tag methods

    public void addTag(Event event, String content) {
        event.addTag(content);
    }

    public void removeTag(Event event, String content) {
        event.removeTag(content);
    }

    public void editTag(Event event, String oldTag, String newTag) {
        event.removeTag(oldTag);
        event.addTag(newTag);
    }

    //3)Alert methods
    private void deleteAlert(Event event, Alert alert) {
        event.removeAlert(alert);
    }

    public void addAlert(Event event, Alert alert) {
        event.addAlert(alert);
    }

    //4)Memo methods
    private void deleteMemo(Event event) {
        event.removeMemo();
    }

    public void addMemo(Event event, Memo memo) {
        event.addMemo(memo);
    }

    //5)getter
    public Event getEvent(int id){
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getId() == id){
                return this.store.get(i);
            }
        }
        return null;
    }

}
