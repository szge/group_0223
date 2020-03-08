import sun.util.resources.CalendarData;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarManager {

    private UserManager userMg;
    private OverallManager overMg;

    public CalendarManager()
    {
        userMg = new UserManager();
        overMg = new OverallManager();

    }

    /**
     * Logs in this user.
     * Uses userMg to confirm if this user exists
     * Asks DataManager to log this user in and retrieve their events/data etc
     * @param user
     * @param pass
     * @return
     * * the ID of the user, if successful
     * * -1 if user does not exist at all
     * * -2 if username exists but password is wrong.
     */
    public int login(String user, String pass){
        int code = userMg.login(user, pass);
        if(code > 0){
            try
            {
                CalendarDataFacade.login(user);
            }
            catch (FileNotFoundException e)
            {
                return -1;
            }
        }
        return code;
    }

    /**
     * Creates a new user via UserManager
     * @param user the username of the user to create
     * @param pass the password of the user to create
     * @return
     * TRUE if a new user was successfully created
     * FALSE if a user with that username exists already
     */
    public boolean createNewUser(String user, String pass){
        /* TODO: create a method createNewUser() in DataManager to correctly set up a new user for access */
        // DataManager.createNewUser(user)
        return userMg.createNewUser(user, pass);
    }

    /**
     * Deletes user by the Specified ID.
     * @param userID the ID of the user to remove
     */
    public void deleteUserID(int userID) {
        userMg.deleteUserByName(userID);
    }

    /**
     * Gets all Events in our calendar
     * @return an array of all events (from DataManager)
     */
    public ArrayList<Event> getEvents() {
        return CalendarDataFacade.getEvents();
    }

    /**
     * Gets the Event matching the specified ID
     * @param id the ID of the event to retrieve
     * @return Event matching the ID
     */
    public Event getEventByID(String id) {
        return overMg.getEvent(Integer.parseInt(id));
    }

    public Event getEventByID(int id) {
        return overMg.getEvent(Integer.parseInt(Integer.toString(id)));
    }

    /**
     * @author Alex, Jonathan
     * @param ids an array of IDs representing the events to delete
     * @return void
     * Iterates through list of event IDs and gets DataManager to delete those events.
     */
    public void deleteEvents(int[] ids) {
        for(int i: ids)
        {
            Event e = getEventByID(i);
            overMg.deleteEvent(e);
        }
        // TODO: Need to confirm that DataManager.deleteEVent will automatically delete associated alerts

        /* TODO: Given a list of event ids, delete all the events corresponding to this user's valid event ids.
        *   If a given ID is not valid, do nothing. Make sure to also delete all the alerts associated with each event*/
    }


    /**
     * @author Jonathan, Alex
     * Given an input of a desired date, returns the list of matching Events from DataManager
     *
     * @param dateInfo A 3 element int input of the format [day, month, year]
     * @return array of Events matching the search date (from DataManager)
     */
    public ArrayList<Event> getEventsByDate(int[] dateInfo) {
        int dayOfMonth = dateInfo[0];
        int month = dateInfo[1];
        int year = dateInfo[2];
        LocalDate searchDate = LocalDate.of(year, month, dayOfMonth);
        return CalendarDataFacade.getEventsByDate(searchDate);
    }


    /**
     * @author Alex, Jonathan
     * @return ArrayList of all Alerts
     */
    public ArrayList<Alert> getAlerts() {
        /* TODO: theoretically DataManager should have a getAlerts() method and this will just call that and return */
        ArrayList<Alert> alerts = CalendarDataFacade.getAlerts();
        return alerts;
    }
}

