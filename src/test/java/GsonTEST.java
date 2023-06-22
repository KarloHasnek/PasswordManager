import controllers.DBHandler;
import models.User;

import java.util.ArrayList;
import java.util.Map;

import static controllers.DBHandler.addToDB;
import static controllers.DBHandler.saveUsersToJson;

public class GsonTEST {

    public static void main(String[] args) {

        User user1 = new User("admin", "admin", "16/10/2020", Map.of("admin", "admin"));

        ArrayList<User> users = new ArrayList<>();

        users.add(user1);

        // Testiranje dodavanja jednog user-a u bazu
        addToDB(user1);

    }
}
