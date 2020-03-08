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
     * @return -1 if the event id is already in its events list, 1 if successfully added
     */
    public int addEvent(Integer id) {
        if (events.contains(id)) {
            return -1; //FAILURE
        } else {
            events.add(id);
            return 1; //SUCCESS
        }
    }

    //Beginning of Arsham's methods
    public void removeEvent(int id){
        this.events.remove(id);
    }
}
