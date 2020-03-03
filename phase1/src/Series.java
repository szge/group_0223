import java.time.LocalDate;
import java.util.ArrayList;

public class Series {
    private String name;
    private ArrayList<Integer> events = new ArrayList<>();

    private int id;
    private static int numSeries = 0;

    public Series(String name) {
        numSeries ++;
        this.id = numSeries;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int addEvent(Event event) {
        if (events.contains(event.getId())) {
            return -1; //FAILURE
        } else {
            events.add(event.getId());
            return 1; //SUCCESS
        }
    }

    public ArrayList<Integer> getEvents() {
        return this.events;
    }

}
