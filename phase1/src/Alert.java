import java.time.*;

public class Alert {

    private String name;
    private LocalDateTime dateTime;

    private int id;
    private static int numAlerts = 0;

    public boolean userAlerted = false;

    public Alert(String name, LocalDateTime when) {
        numAlerts++;
        this.id = numAlerts;
        this.name = name;
        dateTime = when;
    }


    public String toString() {
        return String.format("%s at %s",  name, dateTime.toString());
    }

    public int getId() {
        return id;
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

    //Arsham's code:
    public void changeName(String content){
        this.name  = content;
    }

    public void changeTime(LocalDateTime time){
        this.dateTime = time;
    }

    public String getName(){
        return this.name;
    }
}
