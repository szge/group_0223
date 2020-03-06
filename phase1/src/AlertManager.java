import java.time.*;
import java.util.ArrayList;

public class AlertManager {

    private ArrayList<ArrayList<Alert>> serialAlerts;
    private ArrayList<Alert> store; //holds a list of the Memos

    public AlertManager(){
        this.store = new ArrayList<Alert>();
        this.serialAlerts = new ArrayList<ArrayList<Alert>>();
    }

    public void addAlert(String name, LocalDateTime when){
        Alert newAlert = new Alert(name, when);
        this.store.add(newAlert);
    }

    public void addSerialAlert(){




    }

    public void removeAlert(int id, String alertName){
        for (int i=0; i<this.store.size(); i++){
            if (this.store.get(i).getId() == id) {
                this.store.remove(i);
            }
        }
    }

    public void removeSerialAlert(){


    }

    public ArrayList<Alert> remainingAlert(int id){
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
        return remaining;
        }
    }
}

//add, remove, edit