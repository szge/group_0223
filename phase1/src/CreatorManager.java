import java.util.ArrayList;

public class CreatorManager {

    private EventCreator eventCreator;
    private SeriesCreator seriesCreator;
    private AlertCreator alertCreator;
    private MemoCreator memoCreator;
    private SeriesLinker seriesLinker;
    private MemoLinker memoLinker;

    public CreatorManager()
    {
        eventCreator = new EventCreator();
        seriesCreator = new SeriesCreator();
        alertCreator = new AlertCreator();
        memoCreator = new MemoCreator();
        seriesLinker = new SeriesLinker();
        memoLinker = new MemoLinker();
    }

    public Event createEvent()
    {
        e = EventCreator.createEvent();
        return e;
    }

    public Series createSeries()
    {
        s = SeriesCreator.createSeries();
        return s;
    }

    public Memo createLinkedMemo(ArrayList<Event> events)
    {
        m = MemoLinker.createLinkedMemo(events);
        return m;
    }

    public Series createLinkedSeries(ArrayList<Event> events)
    {
        s = SeriesLinker.createLinkedSeries(events);
        return s;
    }
}
