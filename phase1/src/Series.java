import java.util.ArrayList;

public class Series {
    private String name;
    private ArrayList<Integer> events = new ArrayList<>();

    private int id;
    private static int numSeries = 0;

    public Series(String name) {
        numSeries ++;
        id = numSeries;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Integer> getEvents() {
        return this.events;
    }

    /**
     * Add the id of an event to this memo's list of associated events
     * @param newEvent the event whose id is being added
     */
    public void addEvent(Event newEvent) {
        if (!events.contains(newEvent.getId())) {
            events.add(newEvent.getId());
        }
    }

}
