import java.util.ArrayList;

public class Memo {

    private String contents;
    private ArrayList<Integer> events = new ArrayList<>();

    private int id;
    private static int numMemos = 0;

    public Memo(String contents) {
        numMemos++;
        id = numMemos;
        this.contents = contents;
        events = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getEvents() {
        return events;
    }

    public String toString() {
        return contents;
    }

    /**
     * Add the id of an event to this memo's list of associated events
     * @param id the id of the event being added
     */
    public void addEvent(Integer id) {
        if (!events.contains(id)) {
            events.add(id);
        }
    }

    // remove the Event with a certain ID from the list of Events for a Memo
    public void removeEvent(int id){
        this.events.remove(id);
    }

    // change the name of a memo
    public void changeName(String content){
        this.contents = content;
    }
}
