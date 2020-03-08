import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SeriesManager {

    private ArrayList<Series> store;
    private EventManager eventManager;

    public SeriesManager(EventManager eventManager){
        this.store = new ArrayList<>();
        this.eventManager = eventManager;
    }

    public void createSerialEvent(LocalDateTime startStart, LocalDateTime startEnd,
                                  Duration repetition, LocalDateTime absoluteEnd, String name) {
        Series series = new Series(name);
        while (startEnd.isBefore(absoluteEnd)) {
            series.addEvent(this.eventManager.createEvent(name, startStart, startEnd));
            startStart.plus(repetition);
            startEnd.plus(repetition);
        }
        this.store.add(series);
    }

    public void deleteSerialEvent(Series series){
        for (int i = 0; i < series.getEvents().size(); i++) {
            this.eventManager.deleteEvent(this.eventManager.getEvent(series.getEvents().get(i)));
        }
    }

    public void editSerialEventName(Series series, String name){
        for (int i = 0; i < series.getEvents().size(); i++) {
            this.eventManager.editName(this.eventManager.getEvent(series.getEvents().get(i)), name);
        }
    }

}
