import java.util.ArrayList;

public class Memo {

    private String contents;
    private ArrayList<Event> events;

    public Memo(){
        events = new ArrayList<>();
    }

    public Memo(String contents) {
        this.contents = contents;
        events = new ArrayList<>();
    }

    public String toString()
    {
        return contents;
    }

    /**
     * Add an event to this memo's list of associated events
     * @param newEvent the event being added
     * @return -1 if the event is already in its events list, 1 if success
     */
    public int addEvent(Event newEvent) {
        if (events.contains(newEvent)) {
            return -1; //FAILURE
        }
        else {
            events.add(newEvent);
            return 1; //SUCCESS
        }
    }

    /**
     * getEvents()
     * @return the ArrayList of events this memo is associated with
     */
    public ArrayList<Event> getEvents(){
        return events;
    }

}
