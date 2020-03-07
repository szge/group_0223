import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Scanner;

public class UserInterface {
    public static Scanner sc = new Scanner(System.in);
    public static CalendarManager calendarManager = new CalendarManager();
    public static String currUser;

    public static void start(){
        boolean exit = false;
        System.out.println("Welcome!");
        while(!exit) {
            boolean login = false;
            while(!login) {
                System.out.println("Would you like to login (1), create a new account (2), delete an account (3), or exit the program (4)?: ");
                System.out.println("Please select 1, 2, 3, or 4: ");
                String option1 = sc.nextLine();
                if (option1.equals("1")) {
                    login();
                    login = true;
                } else if (option1.equals("2")) {
                    createAccount();
                } else if (option1.equals("3")) {
                    deleteAccount();
                } else if (option1.equals("4")) {
                    login = true;
                    exit = true;
                } else {
                    System.out.println("Sorry, invalid input. Please try again.");
                }
            }

            if(!exit) {
                System.out.println("Welcome " + currUser);
                boolean logout = false;
                while(!logout){
                    System.out.println("Would you like to:\n(1) View alerts\n(2) View your events\n(3) Logout");
                    System.out.println("Please select 1, 2, or 3:");
                    String option2 = sc.nextLine();
                    if(option2.equals("1")) {
                        viewAlerts();
                    } else if(option2.equals("2")) {
                        viewEvents();
                    } else if(option2.equals("3")) {
                        logout = true;
                    } else {
                        System.out.println("Sorry, invalid input. Please try again.");
                    }
                }
            }
        }
    }

    private static void viewEvents() {
        boolean done = false;

        while(!done){
            System.out.println("Current event list:");



            System.out.println("Would you like to:\n(1) Create an event\n(2) Delete events");
        }
    }

    private static void viewAlerts() {
    }

    public static void login(){
        boolean succ = false;

        while(!succ){
            System.out.println("Please login. Enter your username: ");
            String username = sc.nextLine();
            System.out.println("Enter your password:");
            String password = sc.nextLine();
            int code = calendarManager.login(username, password);

            if(code > 0){
                System.out.println("Login successful. Welcome " + username);
                currUser = username;
                succ = true;
            } else if (code == -1){
                System.out.println("This username does not exist in the database.");
            } else if (code == -2){
                System.out.println("Sorry, the password is incorrect.");
            }
        }
    }

    public static void createAccount(){
        boolean succ = false;

        while(!succ){
            System.out.println("Please enter your new username: ");
            String username = sc.nextLine();
            System.out.println("Please enter your new password: ");
            String password = sc.nextLine();
            succ = calendarManager.createNewUser(username, password);

            if(succ){
                System.out.println("Your profile has been successfully created.");
            } else{
                System.out.println("Sorry, that username already exists.");
            }
        }
    }

    public static void deleteAccount(){
        boolean succ = false;

        while(!succ){
            System.out.println("Please login to delete your profile. Enter your username: ");
            String username = sc.nextLine();
            System.out.println("Enter your password:");
            String password = sc.nextLine();
            int code = calendarManager.login(username, password);

            if(code > 0){
                System.out.println("Account successfully deleted.");
                calendarManager.deleteUserID(code);
                succ = true;
            } else if (code == -1){
                System.out.println("This username does not exist in the database.");
            } else if (code == -2){
                System.out.println("Sorry, the password is incorrect.");
            }
        }
    }
}
