package controllers;

public class AUXCLS {

    public static final String FILE_PATH = "src/main/resources/userDB.csv";

    public static String convertPasswordToString(char[] password) {
        String sb = "";
        for (char c : password) {
            sb += c;
        }
        return sb;
    }
}
