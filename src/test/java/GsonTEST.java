import controllers.DBHandler;
import models.User;

import java.util.ArrayList;
import java.util.Map;

import static controllers.DBHandler.addToDB;
import static controllers.DBHandler.saveUsersToJson;

public class GsonTEST {

    public static void main(String[] args) {

        User user1 = new User("admin", "admin", "16/10/2020", Map.of("admin", "admin"));
        User user2 = new User("user", "user", "16/10/2020", Map.of("user", "user"));

        ArrayList<User> users = new ArrayList<>();

        users.add(user1);
        users.add(user2);

        // Testiranje dodavanja jednog user-a u bazu
        addToDB(user1);

        // Testiranje dodavanja 2. user-a u bazu
        addToDB(user2);

        ArrayList<User> users2 = DBHandler.readDB();
        for (User user : users2) {
            System.out.println(user);
        }
        System.out.println(users2);

        // Testiranje dodavanja 3. user-a u bazu
        User user3 = new User("user3", "user3", "16/10/2020", Map.of("user3", "user3"));
        saveUsersToJson(users);

    }
}
