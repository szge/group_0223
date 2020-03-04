import java.util.ArrayList;

public class Memo {

    private String contents;
    private ArrayList<Integer> events;

    private int id;
    private static int numMemos = 0;

    public Memo(String contents) {
        numMemos++;
        id = numMemos;
        this.contents = contents;
        events = new ArrayList<>();
    }

    public String toString(){
        return contents;
    }

    /**
     * Add the id of an event to this memo's list of associated events
     * @param newEvent the event whose id is being added
     * @return -1 if the event id is already in its events list, 1 if successfully added
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
