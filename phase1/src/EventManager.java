import java.time.LocalDateTime;
        import java.util.ArrayList;
        import java.time.*;

public class EventManager {

    private ArrayList<Event> store; //holds a list of the Memos
    private MemoManager memoManager;
    private AlertManager alertManager;
    private ArrayList<Series> series;

    public EventManager(){
        this.store = new ArrayList<>();
        this.series = new ArrayList<>();
        this.memoManager = new MemoManager();
        this.alertManager = new AlertManager();
    }

    public Event createEvent(String name, LocalDateTime start, LocalDateTime end){
        Event event = new Event(name, start, end);
        this.store.add(event);
        return event;
    }

    public void createEvent(String name, LocalDateTime start, LocalDateTime end, Memo memo){
        Event event = new Event(name, start, end, memo);
        this.store.add(event);
    }

    public void deleteEvent(String name){
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getName().equals(name)){
                this.deleteEventAlerts(this.store.get(i));
                this.deleteEventMemo(this.store.get(i));
                this.store.remove(this.store.get(i));
            }
        }
    }

    private void deleteEventAlerts(Event event){
        for (int j = 0; j < event.getAlerts().size(); j++) {
            this.alertManager.removeAlert(event.getAlerts().get(j));
        }
    }

    private void deleteEventMemo(Event event){
        this.memoManager.removeMemo(event.getMemo().content(), event.getMemo().getid());
        //need a getter for memo content and memo id
    }

    public void createSerialEvent(LocalDateTime startStart, LocalDateTime startEnd,
                                  Duration repetition, LocalDateTime absoluteEnd, String name){
        Series series  = new Series(name);
        while (startEnd.isBefore(absoluteEnd)){
            series.addEvent(this.createEvent(name, startStart, startEnd));
            startStart.plus(repetition);
            startEnd.plus(repetition);
        }
        this.series.add(series);
    }

    public void deleteSerialEvent(Series series){
        for (int i = 0; i < series.getEvents().size(); i++) {
            for (int j = 0; j < this.store.size(); j++) {
                if (series.getEvents().get(i).equals(this.store.get(j).getId())){
                    this.deleteEvent(this.store.get(j));
                }
            }
        }
    }

    public void editEvent(Event event, String newName){
        event.editName(newName); //need a method for this event
    }

    public void editSerialEvent(Series series, String content){
        for (int i = 0; i < series.getEvents().size() ; i++) {
            for (int j = 0; j < this.store.size(); j++) {
                if (this.store.get(j).getId() == series.getEvents().get(i)){
                    this.editEvent(this.store.get(j), content);
                }
            }
        }
    }

    public void editEvent(Event event, String content){
        this.memoManager.removeMemo(event.getMemo.toString(), event.getId()); //need a getter for event's memo
        this.memoManager.addMemo(event.getId(), content);
        event.editMemo(content); //need a method for this event
    }

    public void editEvent(Event event, LocalDateTime newStart){
        event.editStart(newStart); //need a method for this event
    }

    public void editEvent(Event event, LocalDateTime newEnd){
        event.editEnd(newEnd); //need a method for this event
    }


    public void addMemo(Event event, String content){
        event.addMemo(this.memoManager.addMemo(event.getId(), content));
    }
    public void editMemo(Event event, String content){
        this.memoManager.removeMemo(event.getMemo(), event.getId());
        event.addMemo(this.memoManager.addMemo(event.getId(), content));
    }
    public void deleteMemo(Event event){
        this.memoManager.removeMemo(event.getMemo(), event.getId());
        event.removeMemo(); //need to create this method
    }


    public void addAlert(Event event, String name, LocalDateTime when){
        if (when.isBefore(event.getStartDateTime())){
            this.alertManager.addAlert(name, when);
        }
    }

    public void addSerialAlert(Event event, String name, Duration duration, LocalDateTime start, LocalDateTime end){
        ArrayList<Alert> alerts = this.alertManager.addSerialAlert(name, start, end, duration);
        for (int i = 0; i < alerts.size(); i++) {
            event.addAlert(alerts.get(i));
        }
    }

    public void editAlert(Alert alert, String name){
        alert.changeName(name);
    }

    public void editAlert(Alert alert, LocalDateTime time){
        alert.changeTime(time);
    }

    public void deleteAlert(Event event, Alert alert){
        event.removeAlert(alert);
        this.alertManager.removeAlert(alert);
    }

    public void addSerialAlert(Event event, String name, LocalDateTime start, LocalDateTime finish, Duration repetition){
        ArrayList<Alert> alerts = this.alertManager.addSerialAlert(name, start, finish, repetition);
        for (int i = 0; i < alerts.size(); i++) {
            event.addAlert(alerts.get(i));
        }
    }
    public void deleteSerialAlert(Event event, Alert alert){
        ArrayList<Alert> alerts = this.alertManager.removeSerialAlert(alert);
        for (int i = 0; i < alerts.size(); i++) {
            event.removeAlert(alerts.get(i));
        }
    }
    public void editSerialAlert(Event event, Alert alert, String name){
        ArrayList<Alert> alerts = this.alertManager.seriesofAlerts(alert);
        for (int i = 0; i < alert; i++) {

        }
    }


    public void addTag(Event event, String content) {
        event.addTag(content);
    }
    public void removeTag(Event event, String content){
        event.removeTag(content);
    }

    public void editTag(Event event, String oldtag, String newtag){
        event.removeTag(oldtag);
        event.addTag(newtag);
    }
