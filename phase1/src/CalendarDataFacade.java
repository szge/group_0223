import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class CalendarDataFacade {
    private static ArrayList<Event> events = new ArrayList<Event>();
    private static ArrayList<Memo> memos = new ArrayList<Memo>();
    private static ArrayList<Alert> alerts = new ArrayList<Alert>();
    private static ArrayList<Series> series = new ArrayList<Series>();
    private static CalendarDataLoader loader = new CalendarDataLoader();
    private static CalendarDataSaver saver = new CalendarDataSaver();
    private static JSONObject user;

    /**
     * @author Danial
     *
     * Returns all events
     * @return
     * ArrayList<Event>
     */
    public static ArrayList<Event> getEvents() {
        return events;
    }
    /**
     * @author Danial
     *
     * Returns all memos
     * @return
     * ArrayList<Memo>
     */
    public static ArrayList<Memo> getMemos() {
        return memos;
    }
    /**
     * @author Danial
     *
     * Returns all alerts
     * @return
     * ArrayList<Alert>
     */
    public static ArrayList<Alert> getAlerts() {
        return alerts;
    }
    /**
     * @author Danial
     *
     * Returns all series
     * @return
     * ArrayList<Series>
     */
    public static ArrayList<Series> getSeries() {
        return series;
    }

    public static void addMemo(Memo m){
        memos.add(m);
    }

    private static  LocalDate timetoDate(LocalDateTime time){
        int year = time.getYear();
        int month = time.getMonthValue();
        int day = time.getDayOfMonth();
        return LocalDate.of(year, month, day);
    }

    public static void login(String username) throws FileNotFoundException {
        String filename = "ProgramData.json";
        File file = new File(filename);
        JSONParser parser = new JSONParser();
        ArrayList<JSONArray> toBeLoaded = new ArrayList<JSONArray>();
        try{
            FileReader reader = new FileReader(file.getAbsolutePath());
            Object obj = parser.parse(reader);
            JSONObject jsonObj = (JSONObject) obj;
            user = (JSONObject) jsonObj.get(username);
            toBeLoaded.add((JSONArray) user.get("Memos"));
            toBeLoaded.add((JSONArray) user.get("Alerts"));
            toBeLoaded.add((JSONArray) user.get("Events"));
            toBeLoaded.add((JSONArray) user.get("Series"));
        } catch(
                ParseException | IOException e)
        {
            e.printStackTrace();
        }
        ArrayList<ArrayList> attributes = new ArrayList<ArrayList>();
        attributes.add(memos);
        attributes.add(alerts);
        attributes.add(events);
        attributes.add(series);
        loader.loadData(toBeLoaded, attributes);
    }
    public static void logout(){
        saver.saveData(events, memos, alerts, series, user);
        events = new ArrayList<Event>();
        memos = new ArrayList<Memo>();
        alerts = new ArrayList<Alert>();
        series = new ArrayList<Series>();
        user = null;

    }


    public static ArrayList<Event> getEventsByDate(LocalDate date){
        ArrayList<Event> eventsToReturn = new ArrayList<Event>();
        for(Event event: events){
            if(date.equals(timetoDate(event.getStartDateTime()))){
                eventsToReturn.add(event);
            }
        }
        return eventsToReturn;
}
    /**
     * @author Danial
     *
     * Iterates through events and returns the ones
     * whose end date is before today.
     * @return
     * ArrayList<Event>
     */
    public static ArrayList<Event> getPastEvents(){
        LocalDate now = LocalDate.now();
        ArrayList<Event> eventsToReturn = new ArrayList<Event>();
        for(Event event: events){
            if(now.isAfter(timetoDate(event.getEndDateTime()))){
                eventsToReturn.add(event);
            }
        }
        return eventsToReturn;

    }
    /**
     * @author Danial
     *
     * Iterates through events and returns the ones
     * whose start date is after today.
     * @return
     * ArrayList<Event>
     */
    public static ArrayList<Event> getFutureEvents(){
        LocalDate now = LocalDate.now();
        ArrayList<Event> eventsToReturn = new ArrayList<Event>();
        for(Event event: events){
            if(now.isBefore(timetoDate(event.getStartDateTime()))){
                eventsToReturn.add(event);
            }
        }
        return eventsToReturn;

    }
    /**
     * @author Danial
     *
     * Iterates through events and returns the ones
     * whose start date is today or before today and their
     * end date is today or after today. In other words
     * events that are currently in process
     * @return
     * ArrayList<Event>
     */
    public static ArrayList<Event> getCurrentEvents(){
        LocalDate now = LocalDate.now();
        ArrayList<Event> eventsToReturn = new ArrayList<Event>();
        for(Event event: events){
            if(!(now.isBefore(timetoDate(event.getStartDateTime()))) &&
                    !(now.isAfter(timetoDate(event.getEndDateTime())))){
                eventsToReturn.add(event);
            }
        }
        return eventsToReturn;

    }
    /**
     * @author Danial
     *
     * Iterates through events and returns the ones
     * associated with the memo provided
     * @param memo The memo whose events are to be retrieved
     * @return
     * ArrayList<Event>
     */
    public static ArrayList<Event> getEventsByMemo(Memo memo){
        ArrayList<Integer> eventids = memo.getEvents();
        ArrayList<Event> eventsToReturn = new ArrayList<Event>();
        for(Event event: events){
            if(eventids.contains((Integer) event.getId())) {
                eventsToReturn.add(event);
            }
            }
        return eventsToReturn;
    }
    /**
     * @author Danial
     *
     * Iterates through events and returns the ones
     * associated with the tag provided
     * @param tag The tag whose events are to be retrieved
     * @return
     * ArrayList<Event>
     */
    public static ArrayList<Event> getEventsByTag(String tag){
        ArrayList<Event> eventsToReturn = new ArrayList<Event>();
        for(Event event: events){
            if(event.getTags().contains(tag)) {
                eventsToReturn.add(event);
            }
        }
        return eventsToReturn;
    }
    public static void main(String[] args) throws FileNotFoundException {
        login("Danial");
        for(Event event: events){
           System.out.println(event.getId());
           System.out.println(event.getName());
           System.out.println(event.getStartDateTime());
           System.out.println(event.getEndDateTime());
           System.out.println(event.getTags());
        }
    }

}
