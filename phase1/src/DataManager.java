import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class DataManager {
    private static ArrayList<Event> events = new ArrayList<Event>();
    private static ArrayList<Memo> memos = new ArrayList<Memo>();
    private static ArrayList<Alert> alerts = new ArrayList<Alert>();
    private static ArrayList<Series> series = new ArrayList<Series>();
    private static CalenderDataLoader loader = new CalenderDataLoader();
    private static boolean isLoaded = false;

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

//    private static LocalDateTime makeLocalDateTime(JSONObject time){
//        int year = (Integer)time.get("year");
//        int month = (Integer)time.get("month");
//        int day = (Integer)time.get("day");
//        int hour = (Integer)time.get("hour");
//        int min = (Integer)time.get("min");
//        return (LocalDateTime.of(year, month, day, hour, min));
//    }

    private static  LocalDate timetoDate(LocalDateTime time){
        int year = time.getYear();
        int month = time.getMonthValue();
        int day = time.getDayOfMonth();
        return LocalDate.of(year, month, day);
    }

//    private static void loadAlerts(JSONArray jsonalerts){
//        for(Object jalert: jsonalerts){
//            JSONObject jsonalert = (JSONObject) jalert;
//            String name = (String)jsonalert.get("name");
//            JSONObject time = (JSONObject) jsonalert.get("time");
//            LocalDateTime alertTime = makeLocalDateTime(time);
//            alerts.add(new Alert(name, alertTime));
//        }
//    }
//    private static void loadMemos(JSONArray jsonmemos){
//        for(Object jmemo: jsonmemos){
//            JSONObject jsonmemo = (JSONObject) jmemo;
//            String content = (String)jsonmemo.get("content");
//            Memo createdMemo = new Memo(content);
//            for(Object id: (JSONArray)jsonmemo.get("event ids")){
//                createdMemo.addEvent((Integer)id);
//            }
//            memos.add(createdMemo);
//        }
//    }
//    private static void loadEvents(JSONArray jsonevents){
//        for(Object jevent: jsonevents){
//            JSONObject jsonevent = (JSONObject) jevent;
//            String name = (String)jsonevent.get("name");
//            JSONObject stime = (JSONObject) jsonevent.get("start time");
//            JSONObject etime = (JSONObject) jsonevent.get("end time");
//            JSONArray tags = (JSONArray) jsonevent.get("tags");
//            JSONArray alertids = (JSONArray) jsonevent.get("alert ids");
//            LocalDateTime eventStartTime = makeLocalDateTime(stime);
//            LocalDateTime eventEndTime = makeLocalDateTime(etime);
//            Event createdEvent = new Event(name, eventStartTime, eventEndTime);
//            if(jsonevent.get("memo id") != null) {
//                for (Memo m : memos) {
//                    if (m.getId() == (Integer) jsonevent.get("memo id")) {
//                        createdEvent.addMemo(m);
//                    }
//                }
//            }
//            for(Object t: tags){
//                createdEvent.addTag((String) t);
//            }
//            for(Object id: alertids){
//                for(Alert alert: alerts){
//                    if((Integer)id == alert.getId()){
//                        createdEvent.addAlert(alert);
//                    }
//                }
//            }
//            events.add(createdEvent);
//            }
//        }
//
//    private static void loadSeries(JSONArray jsonseries){
//        for(Object js: jsonseries){
//            JSONObject jsonse = (JSONObject) js;
//            String name = (String)jsonse.get("name");
//            Series createdSerie = new Series(name);
//            for(Object id: (JSONArray)jsonse.get("event ids")){
//                for(Event event: events){
//                    if((Integer)id == event.getId()){
//                        createdSerie.addEvent(event);
//                    }
//                }
//            }
//            series.add(createdSerie);
//        }
//        }

    public static void login(String username) throws FileNotFoundException {
        String filename = "ProgramData.json";
        File file = new File(filename);
        JSONParser parser = new JSONParser();
        ArrayList<JSONArray> toBeLoaded = new ArrayList<JSONArray>();
        try{
            FileReader reader = new FileReader(file.getAbsolutePath());
            Object obj = parser.parse(reader);
            JSONObject jsonObj = (JSONObject) obj;
            JSONObject user = (JSONObject) jsonObj.get(username);
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

//    private static void loadData(ArrayList<JSONArray> toBeLoaded) {
//        loadMemos(toBeLoaded.get(0));
//        loadAlerts(toBeLoaded.get(1));
//        loadEvents(toBeLoaded.get(2));
//        loadSeries(toBeLoaded.get(3));
//        isLoaded = true;
//    }

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
//            System.out.println(event.get());
        }
    }

}
