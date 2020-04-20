import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static Scanner sc = new Scanner(System.in);
    public static CalendarManager calendarManager = new CalendarManager();
    public static String currUser;

    public static void start() {
        boolean exit = false;
        System.out.println("Welcome!");
        while (!exit) {
            boolean login = false;
            while (!login) {
                System.out.println("Would you like to login (1), create a new account (2), delete an account (3), or exit the program (4)?: ");
                System.out.println("Please select 1, 2, 3, or 4: ");
                String option1 = sc.nextLine();
                if (option1.equals("1")) {
                    login();
                    if(currUser != null) {
                        login = true;
                    }
                } else if (option1.equals("2")) {
                    createAccount();
                } else if (option1.equals("3")) {
                    deleteAccount();
                } else if (option1.equals("4")) {
                    calendarManager.logout();
                    login = true;
                    exit = true;
                } else {
                    System.out.println("Sorry, invalid input. Please try again.");
                }
            }

            if (!exit) {
                System.out.println("Welcome " + currUser);
                boolean logout = false;
                while (!logout) {
                    System.out.println("Would you like to:\n(1) View/add Memos\n(2) View/add/modify " +
                            "your events\n(3) View To Do list\n(4) Logout");
                    System.out.println("Please select 1, 2, or 3:");
                    String option2 = sc.nextLine();
                    if (option2.equals("1")) {
                        viewAlerts();
                    } else if (option2.equals("2")) {
                        viewEvents();
                    } else if (option2.equals("3")) {
                        viewToDoList();
                    } else if (option2.equals("4")) {
                        logout = true;
                        calendarManager.logout();
                    }else {
                        System.out.println("Sorry, invalid input. Please try again.");
                    }
                }
            }
        }
    }

    private static void viewToDoList() {
    }

    private static void viewEvents() {
        boolean done = false;

        while (!done) {
            System.out.println("Current event list:");
            System.out.println("\n");
            System.out.println("   [id]   |           [name]           |    [Start date]   |    [End date]    |");
            System.out.println("===============================================================================");
            ArrayList<Event> events = calendarManager.getEvents();
            for (Event ev : events) {
                String space = "";
                for(int i=0; i < ((30 - ev.getName().length())/2); i++){
                    space += " ";
                }

                System.out.println("    "+ev.getId()+"    "+space+ev.getName()+space+" "+ev.getStartDateTime()+"     "
                        +ev.getEndDateTime());
            }
            System.out.println("===============================================================================");
            System.out.println("\n");
            System.out.println("Would you like to:\n(1) Create event(s)\n(2) View a specific event (Alerts, Memo, Tags)" +
                    "\n(3) Delete events\n(4) View events by day\n(5) Exit event list");
            System.out.println("Please select 1, 2, 3, 4, or 5:");

            String option3 = sc.nextLine();

            if (option3.equals("1")) {
                createEvents();
                done = true;
            } else if (option3.equals("2")) {
                viewEvent();
                done = true;
            } else if (option3.equals("3")) {
                deleteEvents();
                done = true;
            } else if (option3.equals("4")) {
                viewEventsByDay();
                done = true;
            } else if (option3.equals("5")) {
                done = true;
            } else {
                System.out.println("Sorry, invalid input. Please try again.");
            }
        }
    }

    private static void viewEventsByDay() {
        boolean succ =false;
        while(!succ){
            System.out.println("Please enter the date you wish to access in the format DD-MM-YYYY, e.g. 29-2-2020 or 04-05-1996:");
            String line = sc.nextLine();
            try {
                int[] date = Arrays.stream(line.split("-")).mapToInt(Integer::parseInt).toArray();
                ArrayList<Event> events = calendarManager.getEventsByDate(date);

                System.out.println("Event list on " + Arrays.toString(date) + ":");
                System.out.println("   [id]   |           [name]           |       [date]       |");
                System.out.println("=============================================================");
                /* TODO: Get event list from calendarManager and show it here, with the format
                 *    [id]   |   [name]   |   [date]
                 * ===================================
                 *   30594   | Book Club  |   (some datetime)
                 *   30969   |   Lunch    |   (some datetime)
                 *  */

                for(Event ev : events){
                    System.out.println(ev.toString());
                }

                System.out.println("Press any key to continue.");
                sc.nextLine();

                succ = true;
            } catch (Exception e) {
                System.out.println("Sorry, invalid input. Please try again.");
            }
        }
    }

    private static void deleteEvents() {
        boolean succ = false;
        while(!succ) {
            System.out.println("Please enter a comma separated list of event IDs to delete, e.g. '5, 39, 6'");
            String line = sc.nextLine();
            try {
                int[] delete = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
                calendarManager.deleteEvents(delete);
                System.out.println("Event IDs " + Arrays.toString(delete) + " successfully deleted.");

                succ = true;
            } catch (Exception e) {
                System.out.println("Sorry, invalid input. Please try again.");
            }
        }
    }

    private static void viewEvent() {
        System.out.println("Please enter the id of the event that you wish to view:");
        String id = sc.nextLine();
        Event event = calendarManager.getEventByID(id);
        System.out.println("__________________________________________________________________________________");
        System.out.println("Event ID: "+event.getId()+"     Event Name: "+event.getName() +
                "    From: "+event.getStartDateTime()+ "    To: "+event.getEndDateTime());
        System.out.println("__________________________________________________________________________________");
        if(event.getMemo() == null){
            System.out.println("There isn't a memo associated with this event.");
        }else {
            System.out.println("Memo: "+event.getMemo().toString());
        }
        if(event.getTags().size() == 0){
            System.out.println("There aren't any tags associated with this event.");
        }else {
            String tags = "";
            for (String t:event.getTags()){
               tags += (" #"+ t);
            }
            System.out.println("Tags: "+tags);
        }
        if(event.getAlerts().size() == 0){
            System.out.println("There aren't any alerts associated with this event.");
        }else {
            String alerts = event.getAlerts().get(0).toString();
            for (Alert a:event.getAlerts()){
                if(a != event.getAlerts().get(0)) {
                    alerts += (", " + a.toString());
                }
            }
            System.out.println("Alerts: "+alerts);
        }System.out.println("__________________________________________________________________________________");
        System.out.println("\n");
        System.out.println("Input any key to continue.");
        sc.nextLine();
    }

    private static void createEvents() {
        System.out.println("Welcome to the Event creator. How would you like to create your events?\n(1) Single event\n(2) Recurring event");
        String option = sc.nextLine();
        if(option == "1");{
            System.out.println("Name of the Event:");
            String name = sc.nextLine();
            System.out.println("Date and time of the start of the event if the form YYYY/MM/DD");
            String sdate = sc.nextLine();
            System.out.println("Date and time of the start of the event if the form hh:mm");
            String stime = sc.nextLine();
            String[] arrOfStr = sdate.split("/");
            String[] arrOfStr2 = stime.split(":");
            LocalDateTime star = LocalDateTime.of(Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]),
                    Integer.parseInt(arrOfStr[2]), Integer.parseInt(arrOfStr2[0]), Integer.parseInt(arrOfStr2[0]));
            System.out.println("Date and time of the end of the event if the form YYYY/MM/DD");
            String edate = sc.nextLine();
            System.out.println("Date and time of the start of the event if the form hh:mm");
            String etime = sc.nextLine();
            String[] arrOfStr3 = edate.split("/");
            String[] arrOfStr4 = etime.split(":");
            LocalDateTime end = LocalDateTime.of(Integer.parseInt(arrOfStr3[0]), Integer.parseInt(arrOfStr3[1]),
                    Integer.parseInt(arrOfStr3[2]), Integer.parseInt(arrOfStr4[0]), Integer.parseInt(arrOfStr4[0]));
            calendarManager.createEvent(name, star, end);
        }
    }

    private static void viewAlerts() {
        ArrayList<Alert> alerts = calendarManager.getAlerts();
        for(Alert a : alerts){
            System.out.println(a.toString());
            System.out.println("Press any key to acknowledge this event.");
            sc.nextLine();
        }
        if(alerts.size() == 0){
            System.out.println("You have no alerts waiting to be acknowledged.");
        } else {
            System.out.println("That concludes your current list of alerts. You have no more alerts to be acknowledged.");
        }
        System.out.println("Press any key to continue.");
        sc.nextLine();
    }

    private static void login() {
        boolean succ = false;

        while (!succ) {
            System.out.println("Please login. Enter your username (Alternatively, input 'Esc' to escape): ");
            String username = sc.nextLine();

            if(username.equals("Esc")) break;

            System.out.println("Enter your password:");
            String password = sc.nextLine();
            int code = calendarManager.login(username, password);

            if (code > 0) {
                System.out.println("Login successful. Welcome " + username);
                currUser = username;
                succ = true;
            } else if (code == -1) {
                System.out.println("This username does not exist in the database.");
            } else if (code == -2) {
                System.out.println("Sorry, the password is incorrect.");
            }
        }
    }

    private static void createAccount() {
        boolean succ = false;

        while (!succ) {
            System.out.println("Please enter your new username: ");
            String username = sc.nextLine();
            System.out.println("Please enter your new password: ");
            String password = sc.nextLine();
            succ = calendarManager.createNewUser(username, password);

            if (succ) {
                System.out.println("Your profile has been successfully created.");
            } else {
                System.out.println("Sorry, that username already exists.");
            }
        }
    }

    private static void deleteAccount() {
        boolean succ = false;

        while (!succ) {
            System.out.println("Please login to delete your profile. Enter your username: ");
            String username = sc.nextLine();
            System.out.println("Enter your password:");
            String password = sc.nextLine();
            int code = calendarManager.login(username, password);

            if (code > 0) {
                System.out.println("Account successfully deleted.");
                calendarManager.deleteUserID(code);
                succ = true;
            } else if (code == -1) {
                System.out.println("This username does not exist in the database.");
            } else if (code == -2) {
                System.out.println("Sorry, the password is incorrect.");
            }
        }
    }
}
