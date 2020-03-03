import java.time.*;

public class Alert {

    private String name;
    private LocalDateTime dateTime;

    private int id;

    public Alert(String name, int eid) {
        this.id = 0;
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
