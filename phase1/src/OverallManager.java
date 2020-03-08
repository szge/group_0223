import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.*;

public class OverallManager{

    private MemoManager memoManager;
    private AlertManager alertManager;
    private SeriesManager seriesManager;
    private EventManager eventManager;

    public OverallManager() {
        this.eventManager = new EventManager();
        this.seriesManager = new SeriesManager(this.eventManager);
        this.memoManager = new MemoManager();
        this.alertManager = new AlertManager();
    }

    //Event methods

    public Event getEvent(int id){
        return this.eventManager.getEvent(id);
    }
    public void createEvent(String name, LocalDateTime start, LocalDateTime end) {
        this.eventManager.createEvent(name, start, end);
    }

    public void createEvent(String name, LocalDateTime start, LocalDateTime end, String content) {
        Event event = this.eventManager.createEvent(name, start, end);
        Memo memo = this.memoManager.addMemo(event.getId(), content);
        this.eventManager.addMemo(event, memo);
    }

    public void deleteEvent(Event event) {
        for (int i = 0; i < event.getAlerts().size(); i++) {
            this.alertManager.removeAlert(event.getAlerts().get(i));
        }
        this.memoManager.removeEventMemo(event.getMemo(), event.getId());
    }

    public void editEventName(Event event, String content){
        this.eventManager.editName(event, content);
    }

    public void EditEventStart(Event event, LocalDateTime newStart){
        this.eventManager.editEventStart(event, newStart);
    }

    public void EditEventEnd(Event event, LocalDateTime newEnd){
        this.eventManager.editEventEnd(event, newEnd);
    }

    public void addTag(Event event, String content){
        this.eventManager.addTag(event, content);
    }

    public void removeTag(Event event, String content) {
        this.eventManager.removeTag(event, content);
    }

    public void editTag(Event event, String oldTag, String newTag) {
        this.eventManager.editTag(event, oldTag, newTag);
    }

    //Alerts
    public void addAlert(Event event, String name, LocalDateTime when){
        this.eventManager.addAlert(event, this.alertManager.addAlert(name, when));
    }

    public void removeAlert(Event event, Alert alert) {
        this.eventManager.deleteAlert(event, alert);
        this.alertManager.removeAlert(alert);
    }

    public void editAlertName(Alert alert, String name){
        this.alertManager.edit(alert, name);
    }

    public void editAlertTime( Alert alert, LocalDateTime when){
        this.alertManager.editAlertTime(alert, when);
    }

    public ArrayList<Alert> getRemainingAlerts(int id){
        return this.alertManager.remainingAlert(id);
    }

    //Memo
    public void addMemo(Event event, String content){
        this.eventManager.addMemo(event, this.memoManager.addMemo(this.eventManager.getEvent(event), content));
    }

    public void removeEventMemo(Event event){
        this.memoManager.removeEventMemo(this.eventManager.getMemo(event), this.eventManager.getEvent(event));
    }

    public void editEventMemo(Event event, Memo memo, String content){
        this.eventManager.addMemo(event, this.memoManager.addMemo(this.eventManager.getEvent(event), content));
    }

    public void deleteMemo(Memo memo){
        for (int i = 0; i < this.memoManager.memoEvent(memo).size(); i++) {
            this.eventManager.deleteMemo(this.eventManager.getEvent(this.memoManager.memoEvent(memo).get(i)));
        }
        this.memoManager.deleteMemo(memo);
    }

    public void editMemo(Memo memo, String content){
        this.memoManager.editName(memo, content);
    }

    //Serial Events
    public void addSerialEvent(LocalDateTime startStart, LocalDateTime startEnd,
                               Duration repetition, LocalDateTime absoluteEnd, String name){
        this.seriesManager.createSerialEvent(startStart, startEnd, repetition, absoluteEnd, name);
    }

    public void addSerialEvent(LocalDateTime startStart, LocalDateTime startEnd,
                               Duration repetition, LocalDateTime absoluteEnd, String name, String content){
        Series series = this.seriesManager.createSerialEvent(startStart, startEnd, repetition, absoluteEnd, name);
        for (int i = 0; i < series.getEvents().size(); i++) {
            this.addMemo(series.getEvents().get(i), content);
        }
    }

    public void deleteSerialEvent(Event event){
        this.seriesManager.deleteSerialEvent(this.seriesManager.seriesGetter(event));
    }

    public void editNameSerialEvent(Event event, String name){
        this.seriesManager.editName(this.seriesManager.seriesGetter(event), name);
    }

    //Serial Alerts
    public void addSerialAlerts(Event event, String name, LocalDateTime start,
                                LocalDateTime finish, Duration repetition){
        ArrayList<Alert> alerts = this.alertManager.addSerialAlert(name, start, finish, repetition);
        for (int i = 0; i < alerts.size(); i++) {
            this.eventManager.addAlert(event, alerts.get(i));
        }
    }

    public void deleteSerialAlerts(Event event, Alert alert){
        ArrayList<Alert> alerts = this.alertManager.removeSerialAlert(alert);
        for (int i = 0; i < alerts.size(); i++) {
            this.eventManager.deleteAlert(event, alerts.get(i));
        }
    }

}
