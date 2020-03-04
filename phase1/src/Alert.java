import java.time.*;

public class Alert {

    private String name;
    private LocalDateTime dateTime;

    private int id;
    private static int numAlerts = 0;

    public Alert(String name, int eid) {
        numAlerts++;
        this.id = numAlerts;
        this.name = name;
    }

    public String toString() {
        return String.format("%s at %s",  name, dateTime.toString());
    }

    public void setDateTime(LocalDateTime datetime) {
        dateTime = datetime;
    }

    public LocalDate getLocalDate(){
        return this.dateTime.toLocalDate();
    }

    public LocalTime getLocalTime(){
        return this.dateTime.toLocalTime();
    }

}
