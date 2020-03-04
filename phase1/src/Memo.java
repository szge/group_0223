import java.util.ArrayList;

public class Memo {

    private String contents;
    private ArrayList<Integer> events;

    public Memo(String contents) {
        this.contents = contents;
        events = new ArrayList<>();
    }

    public String toString(){
        return contents;
    }

    /**
     * Add an event to this memo's list of associated events
     * @param newEvent the event being added
     * @return -1 if the event is already in its events list, 1 if success
     */
    public int addEvent(Event newEvent) {
        if (events.contains(newEvent.getId())) {
            return -1; //FAILURE
        } else {
            events.add(newEvent.getId());
            return 1; //SUCCESS
        }
    }

    public ArrayList<Integer> getEvents(){
        return events;
    }
}
