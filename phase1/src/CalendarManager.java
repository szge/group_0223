import java.util.ArrayList;

public class CalendarManager {

    private CreatorManager creatorManager;
    private DatabaseManager databaseManager;

    public CalendarManager()
    {
        creatorManager = new CreatorManager();
        databaseManager = new DatabaseManager();
    }

    public void createEvent()
    {
        e = creatorManager.createEvent();
        databaseManager.addEvent(e);
    }

    public void createSeries()
    {
        s = creatorManager.createSeries();
        databaseManager.addSeries(s);
    }

    public void viewEvents()
    {
        ArrayList<Event> allEvents = databaseManager.getAllEvents();
        for(Event e: allEvents)
        {
            System.out.println(e.toString());
            System.out.println();
        }
    }

}
