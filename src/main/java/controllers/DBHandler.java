package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.User;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import static controllers.AUXCLS.JSON_FILE_PATH;

public class DBHandler {

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
            JOptionPane.showMessageDialog(null, "No database found!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return users;
    }

    public static Map<String, String> readCredentials(ArrayList<User> users) {
        return null;
    }

    public static User getUser(ArrayList<User> users, String username) {
        return null;
    }

    public static boolean isUsernamePresent(String username) {
        return false;
    }

    public static void addToDB(User user) {
        ArrayList<User> users = readDB();
        if (users == null) users = new ArrayList<>();

        users.add(user);

        clearDB();

        saveUsersToJson(users);
    }

    private static void clearDB() {
        try (Writer writer = new FileWriter(JSON_FILE_PATH)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveUsersToJson(ArrayList<User> users) {
        try (Writer writer = new FileWriter(JSON_FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
