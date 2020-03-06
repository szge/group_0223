/**
 * @author Alex
 *
 * Much of the JSON parsing code was adapted from:
 * https://howtodoinjava.com/library/json-simple-read-write-json-examples/
 */


import java.io.FileReader;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class UserManager {

    ArrayList<JSONObject> users;

    /**
     * @author Alex
     *
     * UserManager constructor
     * Upon creation, it reads from users.json and parses the JSON.
     * User information (username, password, id) is stored in a JSONObject for each user.
     * An ArrayList of user JSONObjects called users stores everything.
     */
    public UserManager()
    {
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("users.json"))
        {
            Object obj = jsonParser.parse(reader);

            users = (JSONArray) obj;

        } catch(FileNotFoundException e) {
            System.out.println("Users.txt not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Alex
     * @param username the username being used to log in
     * @param password the password being used to log in
     * @return
     * int representing the ID of the confirmed user
     * -1 if user does not exist at all
     * -2 if username exists but password is wrong.
     */
    public int login(String username, String password) {
        for (JSONObject usr : users) {
            if (((String) usr.get("username")).equals(username)) {
                if (((String) usr.get("password")).equals(password)) {
                    return (int) usr.get("id");
                }
                return -2;
            }
        }
        return -1;
    }


    /**
     * @author Alex
     *
     * Creates a new user.
     * Adds the user to the working ArrayList of users.
     * Also appends this user to the users.json file.
     *
     * @param username the username of the user to add
     * @param password the password of the user
     * @return
     * FALSE if a matching username already exists
     * TRUE if the user is successfully added.
     */
    public Boolean createNewUser(String username, String password)
    {
        for (JSONObject usr: users) {
            if (((String) usr.get("username")).equals(username)) {
                return Boolean.FALSE; // a user with that name already exists
            }
        }
        // if we've reached this point, then the username does not previously exists
        // so we create a new user
        JSONObject newUser = new JSONObject();
        newUser.put("username", username);
        newUser.put("password", password);
        newUser.put("id", getNextAvailableID());
        users.add(newUser);

        try(FileWriter file = new FileWriter("users.json", true)) {
            file.write(newUser.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Boolean.TRUE;
    }

    /**
     * @author Alex
     *
     * Iterates through the users list and returns the next available ID.
     *
     * Looks through the users list, finds the highest ID, returns the next int up from that.
     * Does not look for the lowest available number.
     *
     * @return the next appropriate ID
     */
    private int getNextAvailableID()
    {
        if (users.size() == 0) {
            return 1;
        }
        int curr_max = 0;
        for(JSONObject usr: users)
        {
            int userID = (int) usr.get("id");
            if ((int) usr.get("id") > curr_max)
             {
                 curr_max = userID;
             }
        }
        return (curr_max+1);
    }

}
