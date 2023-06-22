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
        ArrayList<User> users;

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
            users = new ArrayList<>();
        }

        return users;
    }

    public static Map<String, String> readCredentials(ArrayList<User> users) {

        Map<String, String> usersCredentials = new HashMap<>();

        for (User user : users) {
            usersCredentials.put(user.getUsername(), user.getPassword());
        }

        return usersCredentials;
    }

    public static User getUser(ArrayList<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public static boolean isUsernamePresent(String username) {
        ArrayList<User> users = readDB();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

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
