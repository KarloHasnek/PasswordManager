package controllers;

import models.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class DBHandler {

    public static Set<User> readDB() throws IOException {
        Set<User> data = new HashSet<>();
        Reader in = new FileReader(AUXCLS.FILE_PATH);
        final String[] HEADERS = {"Name", "Surname", "Age", "Username", "Password"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);

        for (CSVRecord record : records) {
            // Used to create instances of User
            Map<String, String> credentials = new HashMap<>();
            String username = record.get("Username");
            String password = record.get("Password");
            String name = record.get("Name");
            String surname = record.get("Surname");
            String age = record.get("Age");
            credentials.put(username, password);

            User user = new User(name, surname, age, credentials);
            data.add(user);
        }

        return data;
    }

    public static Map<String, String> readCredentials(Set<User> users) {
        Map<String, String> credentials = new HashMap<>();

        for (User user : users) {
            credentials.putAll(user.getCredentials());
        }

        return credentials;
    }

    public static void addToDB(String name, String surname, String age, String username, String password) throws IOException {
        File file = new File(AUXCLS.FILE_PATH);
        FileWriter writer = new FileWriter(file, true);
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        printer.printRecord(name, surname, age, username, password);
        printer.close();
    }

    public static boolean isUsernamePresent(String username) {
        Set<User> users = null;
        try {
            users = readDB();
        } catch (IOException e) {
            System.out.println("Something went wrong with database!");
            String message3 = "Our database currently has problems.\nPlease try again later.";
            JOptionPane.showMessageDialog(null, message3, "Oops!", JOptionPane.WARNING_MESSAGE);
        }
        Map<String, String> credentials = readCredentials(users);

        return credentials.containsKey(username);
    }

    public static User getUser(Set<User> users, String username) {
        User user = null;
        for (User u : users) {
            if (u.getCredentials().containsKey(username)) {
                user = u;
                break;
            }
        }
        return user;
    }
}
