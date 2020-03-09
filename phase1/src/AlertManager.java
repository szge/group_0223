//author: Arsham
import java.time.*;
import java.util.ArrayList;

public class AlertManager {

    private ArrayList<ArrayList<Alert>> serialAlerts;
    private ArrayList<Alert> store; //holds a list of the Memos

    public AlertManager(){
        this.store = CalendarDataFacade.getAlerts();
        this.serialAlerts = new ArrayList<ArrayList<Alert>>();
    }

    public Alert addAlert(String name, LocalDateTime when){
        //create and alert
        Alert newAlert = new Alert(name, when);
        this.store.add(newAlert);
        return newAlert;
    }

    public ArrayList<Alert> addSerialAlert(String name, LocalDateTime start, LocalDateTime finish, Duration repetition){
        // create a serial set of alerts
        ArrayList<Alert> alerts = null;
        while(start.isBefore(finish)){
            alerts.add(this.addAlert(name, start));
            start.plus(repetition);
        }
        return alerts;
    }

    public void editName(Alert alert, String content){
        //edits the name of an alert
        alert.changeName(content);
    }

    public void removeAlert(Alert alert){
        this.store.remove(alert);
    } //remove an alert

    public void editAlertTime(Alert alert, LocalDateTime when){
        alert.changeTime(when);
    }// change alert time

    public ArrayList<Alert> removeSerialAlert(Alert alert){
        //remove a series of alerts containing alert.
        ArrayList<Alert> alerts= null;
        for (int i = 0; i < this.serialAlerts.size() ; i++) {
            if (this.serialAlerts.get(i).contains(alert)){
                for (int j = 0; j < this.serialAlerts.get(i).size() ; j++) {
                    alerts.add(this.serialAlerts.get(i).get(j));
                    this.serialAlerts.get(i).remove(j);
                    this.store.remove(this.serialAlerts.get(i).get(j));
                }
            }
        }
        return alerts;
    }

    public ArrayList<Alert> seriesofAlerts(Alert alert){
        //returns a series of alerts containing alert
        ArrayList<Alert> alerts= null;
        for (int i = 0; i < this.serialAlerts.size() ; i++) {
            if (this.serialAlerts.get(i).contains(alert)) {
                for (int j = 0; j < this.serialAlerts.get(i).size(); j++) {
                    alerts.add(this.serialAlerts.get(i).get(j));
                }
            }
        }
        return alerts;
    }

    public void editSeriesOfAlerts(Alert alert, String name){
        //edit the name of an alert series
        ArrayList<Alert> alerts = this.seriesofAlerts(alert);
        for (int i = 0; i < alerts.size(); i++) {
            alerts.get(i).changeName(name);
        }
    }

    public ArrayList<Alert> remainingAlert(int id){
        //return the alerts that haven't been passed
        Alert alerts = null;
        ArrayList<Alert> remaining = new ArrayList<Alert>();
        for (int i=0; i<this.store.size(); i++){
            if (this.store.get(i).getId() == id){
                alerts = this.store.get(i);
            }
        }
        for (int i=0; i<this.store.size(); i++){
            if (this.store.get(i).getLocalDateTime().isBefore(alerts.getLocalDateTime())){
                remaining.add(this.store.get(i));
            }
        }
        return remaining;
    }

}

