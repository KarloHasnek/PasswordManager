package models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.User;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import static controllers.AUXCLS.JSON_FILE_PATH;

/**
 * This class is used for manipulating the "database" which is in this case JSON local file.
 */
public class DBHandler {

    /**
     * Reads the JSON file and returns the list of users.
     * @return ArrayList of users.
     */
    public static ArrayList<User> readDB() {
        Gson gson = new Gson();
        ArrayList<User> users = new ArrayList<>();

        File file = new File(JSON_FILE_PATH);

        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type userListType = new TypeToken<ArrayList<User>>() {
                }.getType();
                users = gson.fromJson(reader, userListType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File doesn't exist!");
        }

        return users;
    }

    /**
     * Reads the list of users and returns the map of usernames and passwords.
     * @param users ArrayList of users.
     * @return Map of usernames and passwords.
     */
    public static Map<String, String> readCredentials(ArrayList<User> users) {

        Map<String, String> usersCredentials = new HashMap<>();

        for (User user : users) {
            usersCredentials.put(user.getUsername(), user.getPassword());
        }

        return usersCredentials;
    }

    /**
     * Returns the user with the given username.
     * @param users ArrayList of users.
     * @param username Username of the user.
     * @return User with the given username.
     */
    public static User getUser(ArrayList<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    /**
     * Checks if the given username is present in the list of users.
     * @param username Username of the user.
     * @return True if the username is present, otherwise false.
     */
    public static boolean isUsernamePresent(String username) {
        ArrayList<User> users = readDB();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds the user to the list of users and saves it to the JSON file.
     * @param user User to be added.
     */
    public static void addToDB(User user) {
        ArrayList<User> users = readDB();
        if (users == null) users = new ArrayList<>();

        users.add(user);

        clearDB();

        saveUsersToJson(users);
    }

    /**
     * Clears the JSON file.
     */
    private static void clearDB() {
        try (Writer writer = new FileWriter(JSON_FILE_PATH)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the list of users to the JSON file.
     * @param users ArrayList of users.
     */
    public static void saveUsersToJson(ArrayList<User> users) {
        try (Writer writer = new FileWriter(JSON_FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the user's password for the given website.
     * @param user User whose password is being updated.
     * @param website Website for which the password is being updated.
     * @param username Username for the given website.
     * @param password Password for the given website.
     */
    public static void updatingUsersPasswords(User user, String website, String username, String password) {
        ArrayList<User> users = readDB();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                u.setWebsiteCredentialsToMap(website, username, password);
            }
        }

        saveUsersToJson(users);
    }

    /**
     * Removes the user from the list of users and saves it to the JSON file.
     * @param user User to be removed.
     */
    public static void removeFromDB(User user) {
        ArrayList<User> users = readDB();
        users.removeIf(u -> u.getUsername().equals(user.getUsername()));

        System.out.println(users);

        saveUsersToJson(users);
    }
}
