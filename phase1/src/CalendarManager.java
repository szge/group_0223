//import java.util.ArrayList;
//
//public class CalendarManager {
//
//    private CreatorManager creatorManager;
//    private DatabaseManager databaseManager;
//
//    public CalendarManager()
//    {
//        creatorManager = new CreatorManager();
//        databaseManager = new DatabaseManager();
//    }
//
//    public void createEvent()
//    {
//        e = creatorManager.createEvent();
//        databaseManager.addEvent(e);
//    }
//
//    public void createSeries()
//    {
//        s = creatorManager.createSeries();
//        databaseManager.addSeries(s);
//    }
//
//    public void viewEvents()
//    {
//        ArrayList<Event> allEvents = databaseManager.getAllEvents();
//        for(Event e: allEvents)
//        {
//            System.out.println(e.toString());
//            System.out.println();
//        }
//    }
//
//}

public class CalendarManager {
    public UserManager userMg = new UserManager();

    public int login(String user, String pass){
        int code = userMg.login(user, pass);
        if(code > 0){
            // TODO: get this to work
//            DataManager.login(user);
        }
        return code;
    }

    public boolean createNewUser(String user, String pass){
        /* TODO: create a method createNewUser() in DataManager to correctly set up a new user for access */
        // DataManager.createNewUser(user)
        return userMg.createNewUser(user, pass);
    }

    public void deleteUserID(int userID) {
        userMg.deleteUserByName(userID);
    }

    public Event[] getEvents() {
        /* TODO: theoretically DataManager should have a getEvents() method and this will just call that and return */
        return new Event[0];
    }

    public Event getEventByID(String id) {
        /* TODO: Get this event by id */
        return null;
    }

    public void deleteEvents(int[] ids) {
        /* TODO: Given a list of event ids, delete all the events corresponding to this user's valid event ids.
        *   If a given ID is not valid, do nothing. Make sure to also delete all the alerts associated with each event*/
    }


    public Event[] getEventsByDate(int[] delete) {
        /* TODO: Given an input of a 3 element int input of the format [day, month, year], call the getEventsByDay()
        *   method in DataManager and return */
        return new Event[0];
    }


    public Alert[] getAlerts() {
        /* TODO: theoretically DataManager should have a getAlerts() method and this will just call that and return */
        return new Alert[0];
    }
}

