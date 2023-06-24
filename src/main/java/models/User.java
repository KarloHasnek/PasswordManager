package models;

import java.util.HashMap;
import java.util.Map;

/**
 * User class used for creating instances of users upon registration.
 */
public class User {

    private String name;
    private String surname;
    private String age;
    private Map<String, String> credentials;
    private Map<String, Map<String, String>> passwords;

    /**
     * Constructor for User class.
     * @param name
     * @param surname
     * @param age
     * @param credentials Map of username and password.
     */
    public User(String name, String surname, String age, Map<String, String> credentials) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.credentials = credentials;
        this.passwords = new HashMap<>();
    }

    /**
     * Retrieves the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the surname of the user.
     * @return The surname of the user.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the user.
     * @param surname The surname of the user.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Retrieves the age of the user.
     * @return The age of the user.
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets the age of the user.
     * @param age The age of the user.
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Retrieves the credentials of the user.
     * @return A map representing the credentials of the user.
     */
    public Map<String, String> getCredentials() {
        return credentials;
    }

    /**
     * Retrieves the username from the credentials.
     * @return The username associated with the credentials.
     */
    public String getUsername() {
        return credentials.keySet().iterator().next();
    }

    /**
     * Retrieves the password from the credentials.
     * @return The password associated with the credentials.
     */
    public String getPassword() {
        return credentials.values().iterator().next();
    }

    /**
     * Retrieves the passwords associated with different websites.
     * @return A map representing the passwords associated with websites.
     */
    public Map<String, Map<String, String>> getPasswords() {
        return passwords;
    }

    /**
     * Sets the credentials of the user.
     * @param credentials A map representing the credentials of the user.
     */
    public void setCredentials(HashMap<String, String> credentials) {
        this.credentials = credentials;
    }

    /**
     * Sets the credentials for a specific website.
     * @param website  The website for which credentials are being set.
     * @param username The username for the website.
     * @param password The password for the website.
     */
    public void setWebsiteCredentialsToMap(String website, String username, String password) {
        passwords.put(website, Map.of(username, password));
    }

    /**
     * Returns a string representation of the User object.
     * @return A string representation of the User object.
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname=" + surname +
                ", age=" + age +
                ", credentials=" + credentials +
                ", passwords=" + passwords +
                '}';
    }

    /**
     * Retrieves the map of website credentials.
     * @return A map representing the website credentials.
     */
    public Map<String, Map<String, String>> getWebsiteCredentialsMap() {
        return passwords;
    }
}
