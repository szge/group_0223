import java.time.*;
import java.util.ArrayList;

public class AlertSeries {
    private String name;
    private ArrayList<Integer> alerts;

    private int id;
    private static int numAlertSeries = 0;

    public AlertSeries(String name) {
        numAlertSeries ++;
        id = numAlertSeries;
        this.name = name;
        this.alerts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Integer> getAlerts() {
        return alerts;
    }

    public static void bringDownNum() {
        this.numAlertSeries --;
    }

    /**
     * Add the id of an Alert to this Series's list of associated Alerts
     * @param newAlert the event whose id is being added
     */
    public void addAlert(Alert newAlert) {
        if (!alerts.contains(newAlert.getId())) {
            alerts.add(newAlert.getId());
        }
    }

    // remove an alert
    public void removeAlert(Alert alert){
        alerts.remove(alert);
    }

    public void editAlertTime(int id, LocalDateTime when) {
        for (int i=0; i<alerts.size(); i++) {
            if (alerts.get(i).getId() == id) {
                alerts.get(i).changeTime(when);
                }
            }
        }

    public ArrayList<Alert> remainingAlert(){
        //return the alerts that haven't been passed
        ArrayList<Alert> remaining = new ArrayList<Alert>();
        for (int i=0; i<alerts.size(); i++){
            if (alerts.get(i).getLocalDateTime().isBefore(LocalDate.now())){
                remaining.add(this.alerts.get(i));
            }
        }
        return remaining;
    }

    public void editName(int id, String name) {
        for (int i = 0; i < alerts.size(); i++) {
            if (alerts.get(i).getId() == id) {
                alerts.get(i).changeName(name);
            }
        }
    }

    public void removeAlert(int id){
        for (int i = 0; i < alerts.size(); i++) {
            if (alerts.get(i).getId() == id) {
                alerts.remove(alerts.get(i));
            }
        }
    }

}
