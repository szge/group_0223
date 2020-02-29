import java.util.ArrayList;

public class EventManager {


    private ArrayList<Event> events;

    public void createAndAddEvent()
    {
        Event newEvent = new Event();
        events.add(newEvent);
    }

    public Event getEventById(int id) {
        for (Event e : events) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
