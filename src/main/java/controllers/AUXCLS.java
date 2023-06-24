package controllers;

/**
 * This is a helper class for the project.
 */
public class AUXCLS {

    /**
     * The path to the JSON file.
     */
    public static final String JSON_FILE_PATH = "src/main/resources/users.json";

    /**
     * This method converts a char array to a String.
     *
     * @param password the char array to be converted
     * @return the converted String
     */
    public static String convertPasswordToString(char[] password) {
        String sb = "";
        for (char c : password) {
            sb += c;
        }
        return sb;
    }
}
