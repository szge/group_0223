import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    static JFrame f;

    static JPanel panelMain, panelSidebar, panelContent;

    static JButton buttonLoginLogout, buttonRegister, buttonAlerts, buttonCalendars;

    static JLabel labelUsername;

    static String currUser;

    public static CalendarManager calendarManager = new CalendarManager();

    public static void start(){
        // Create the main JFrame.
        f = new JFrame("panel");

        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());

        // Create sidebar panel and fill it with contents.
        panelSidebar = new JPanel();
        panelSidebar.setLayout(new GridLayout(10, 1));
        panelSidebar.setSize(100, panelMain.getHeight());
        labelUsername = new JLabel("Not Logged In!", SwingConstants.CENTER);
        buttonLoginLogout = new JButton("Login/Logout");

        buttonLoginLogout.addActionListener(e -> login());

        buttonRegister = new JButton("Register");

        buttonRegister.addActionListener(e -> register());

        buttonAlerts = new JButton("Alerts");

        buttonAlerts.addActionListener(e -> panelContent.setBackground(Color.YELLOW));

        buttonCalendars = new JButton("Calendars");

        panelSidebar.add(labelUsername);
        panelSidebar.add(buttonLoginLogout);
        panelSidebar.add(buttonRegister);
        panelSidebar.add(buttonAlerts);
        panelSidebar.add(buttonCalendars);

        panelSidebar.setSize(300, panelMain.getHeight());
        panelSidebar.setBackground(Color.LIGHT_GRAY);
        panelMain.add(panelSidebar, BorderLayout.WEST);

        // Create contents panel and fill it with contents.

        panelContent = new JPanel();

        //panelContent.setBackground(Color.BLUE);

        panelMain.add(panelContent, BorderLayout.CENTER);


        // add panel to frame
        f.add(panelMain);

        // set the size of frame
        f.setSize(900, 600);

        f.setVisible(true);
    }

    public static void login(){
        // TODO: Refresh current contents

        // Create popup window and contents
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new GridLayout(4, 1));
        JTextField textFieldUsername = new JTextField(20);
        JPasswordField textFieldPassword = new JPasswordField(20);
        panelLogin.add(new JLabel("Username:"));
        panelLogin.add(textFieldUsername);
        panelLogin.add(new JLabel("Password:"));
        panelLogin.add(textFieldPassword);

        String username, password;

        int result = JOptionPane.showConfirmDialog(null, panelLogin,
                "Please Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            username = textFieldUsername.getText();
            password = new String(textFieldPassword.getPassword());

            int code = calendarManager.login(username, password);

            if (code == -1) {
                // This username does not exist in the database
                JOptionPane.showMessageDialog(null, "The user was not found. Please try again.");
                login();
            } else if (code == -2) {
                // The password is incorrect
                JOptionPane.showMessageDialog(null, "Sorry, the password is incorrect. Please try again.");
                login();
            } else {
                // Successful login
                JOptionPane.showMessageDialog(null, "Login successful. Welcome " + username);
                currUser = username;
                labelUsername.setText("Welcome " + username);
            }
        }
    }

    public static void register(){
        JPanel panelRegister = new JPanel();
        panelRegister.setLayout(new GridLayout(6, 1));
        JTextField textFieldUsername = new JTextField(20);
        JPasswordField textFieldPassword = new JPasswordField(20);
        JPasswordField textFieldConfirmPassword = new JPasswordField(20);
        panelRegister.add(new JLabel("Username:"));
        panelRegister.add(textFieldUsername);
        panelRegister.add(new JLabel("Password:"));
        panelRegister.add(textFieldPassword);
        panelRegister.add(new JLabel("Confirm Password:"));
        panelRegister.add(textFieldConfirmPassword);
        
        String username, password, confirmPassword;

        int result = JOptionPane.showConfirmDialog(null, panelRegister,
                "Please Register", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            username = textFieldUsername.getText();
            password = new String(textFieldPassword.getPassword());
            confirmPassword = new String(textFieldConfirmPassword.getPassword());

            if(!password.equals(confirmPassword)) {
                // The password and confirm password fields do not match
                JOptionPane.showMessageDialog(null, "The passwords do not match. Please try again.");
                register();
            } else {
                if(calendarManager.createNewUser(username, password)){
                    // Successful register
                    JOptionPane.showMessageDialog(null, "User successfully created.");
                    // Log in user
                    JOptionPane.showMessageDialog(null, "Login successful. Welcome " + username);
                    currUser = username;
                    labelUsername.setText("Welcome " + username);
                } else {
                    // This username already exists
                    JOptionPane.showMessageDialog(null, "This username already exists. Please try again or login.");
                    register();
                }
            }
        }
    }
}