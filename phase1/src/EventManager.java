//author: Arsham
import java.util.ArrayList;
import java.time.*;

public class EventManager {

    private ArrayList<Event> store;

    public EventManager(ArrayList<Event> store) { this.store = store; }

    public void postponeIndef(int id){
        this.getEvent(id).postpone();
    }

    public Event duplicateEvent(int id){
        Event event = this.getEvent(id);
        return this.createEvent(event.getName(), event.getStartDateTime(), event.getEndDateTime());
    }


    //1)Basics for events

    public Event createEvent(String name, LocalDateTime start, LocalDateTime end) {
        //creates an event
        Event event = new Event(name, start, end);
        this.store.add(event);
        return event;
    }

    public void deleteEvent(Event event) {
        this.store.remove(event);
    } //deletes an event

    public void editEventStart(Event event, LocalDateTime newStart) {
        //edits the starting time of an event
        event.editStart(newStart);
    }

    public void editEventEnd(Event event, LocalDateTime newEnd) {
        //edits the end time of an event
        event.editEnd(newEnd);
    }

    public void editName(Event event, String newName) {
        //edit the name of an event
        event.editName(newName);
    }


    //2)Tag methods

    public void addTag(Event event, String content) {
        event.addTag(content);
    } //add a tag

    public void removeTag(Event event, String content) {
        event.removeTag(content);
    } //remove a tag

    public void editTag(Event event, String oldTag, String newTag) {
        //edit a tag by removing the old tag and adding a new tag
        event.removeTag(oldTag);
        event.addTag(newTag);
    }

    //3)Alert methods
    public void deleteAlert(Event event, Alert alert) {
        event.removeAlert(alert);
    } //deleting an alert from an event

    public void addAlert(Event event, Alert alert) {
        event.addAlert(alert);
    } //add an alert



    //4)Memo methods
    public void deleteMemo(Event event) {
        event.removeMemo();
    } //delete a memo

    public void addMemo(Event event, Memo memo) {
        event.addMemo(memo);
    } //add/replace a memo

    //5)Getter methods
    public Event getEvent(int id){
        //get an event with id
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getId() == id){
                return this.store.get(i);
            }
        }
        return null;
    }

    public int getEvent(Event event){
        return event.getId();
    } //get the id of an event

    public Memo getMemo(Event event){
        return event.getMemo();
    } //get the memo of an event

}
