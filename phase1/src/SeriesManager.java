import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SeriesManager {

    private ArrayList<Series> store;
    private EventManager eventManager;

    public SeriesManager(EventManager eventManager){
        this.store = CalendarDataFacade.getSeries();
        this.eventManager = eventManager;
    }

    public Series createSerialEvent(LocalDateTime startStart, LocalDateTime startEnd,
                                   Duration repetition, LocalDateTime absoluteEnd, String name) {
        Series series = new Series(name);
        while (startEnd.isBefore(absoluteEnd)) {
            series.addEvent(this.eventManager.createEvent(name, startStart, startEnd));
            startStart.plus(repetition);
            startEnd.plus(repetition);
        }
        this.store.add(series);
        return series;
    }

    public void deleteSerialEvent(Series series){
        for (int i = 0; i < series.getEvents().size(); i++) {
            this.eventManager.deleteEvent(this.eventManager.getEvent(series.getEvents().get(i)));
        }
    }

    public void editName(Series series, String name){
        for (int i = 0; i < series.getEvents().size(); i++) {
            this.eventManager.editName(this.eventManager.getEvent(series.getEvents().get(i)), name);
        }
    }

    public Series seriesGetter(Event event){
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getEvents().contains(event.getId())){
                return this.store.get(i);
            }
        }
        return null;
    }
}
