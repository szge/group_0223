
 //Author: Arsham
 import java.time.Duration;
 import java.time.LocalDateTime;
 import java.util.ArrayList;

 public class SeriesManager {

    private ArrayList<Series> store;

    public SeriesManager(ArrayList<Series> store){
        this.store = store;
    }

    public void addSerialEvent(Series series){
         this.store.add(series);
    }

    public void deleteSerialEvent(Series series){
        this.store.remove(series);
    }

    public void editName(Series series, String name){
        series.changeName(name);
    }

    public ArrayList<Event> eventsGetter(Series series, Event event){
        return series.getEvents();
    }
 }

