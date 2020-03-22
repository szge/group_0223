import java.time.*;

public class Alert {

    private String name;
    private LocalDateTime dateTime;

    private int id;
    private static int numAlerts = 0;

    public boolean userAlerted = false;

    public Alert(String name, LocalDateTime when) {
        numAlerts++;
        id = numAlerts;
        this.name = name;
        dateTime = when;
    }

    public String toString() {
        return String.format("%s at %s",  name, dateTime.toString());
    }

    public int getId() {
        return id;
    }

    public static void bringDownNum() {
        this.numAlerts --;
    }

    public LocalDate getLocalDate() {
        return this.dateTime.toLocalDate();
    }

    public LocalTime getLocalTime() {
        return this.dateTime.toLocalTime();
    }

    public LocalDateTime getLocalDateTime() {
        return dateTime;
    }

    // changeName changes the name/message of the alert
    public void changeName(String content){
        this.name  = content;
    }

    // changeTime changes the time of the alert
    public void changeTime(LocalDateTime time){
        this.dateTime = time;
    }

    // getName returns the name/message of the alert
    public String getName(){
        return this.name;
    }
}
