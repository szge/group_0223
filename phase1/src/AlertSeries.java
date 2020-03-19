import java.util.ArrayList;

public class AlertSeries {
    private String name;
    private ArrayList<Integer> alerts = new ArrayList<>();

    private int id;
    private static int numSeries = 0;

    public AlertSeries(String name) {
        numSeries ++;
        id = numSeries;
        this.name = name;
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

    /**
     * Add the id of an Alert to this Series's list of associated Alerts
     * @param newAlert the event whose id is being added
     */
    public void addAlert(Alert newAlert) {
        if (!alerts.contains(newAlert.getId())) {
            alerts.add(newAlert.getId());
        }
    }
}
