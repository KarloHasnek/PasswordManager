package models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private String surname;
    private String age;
    private Map<String, String> credentials;
    private Map<String, Map<String, String>> passwords;

    public User(String name, String surname, String age, Map<String, String> credentials) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.credentials = credentials;
        this.passwords = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public String getUsername() {
        return credentials.keySet().iterator().next();
    }

    public String getPassword() {
        return credentials.values().iterator().next();
    }

    public void setCredentials(HashMap<String, String> credentials) {
        this.credentials = credentials;
    }

    public void addPassword(String website, String password, String username) {
        Map<String, String> passwordInfo = new HashMap<>();
        passwordInfo.put(username, password);
        passwords.put(website, passwordInfo);
    }

    @Override
    public String toString() {
        return "User -> [" + "name='" + name + '\'' + ", surname='" + surname + '\'' + ", username=" + credentials.keySet() + ']';
    }
}
