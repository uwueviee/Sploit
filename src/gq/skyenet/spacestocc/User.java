package gq.skyenet.spacestocc;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utilities relating to the user
 *
 * @author Skye Viau (PretzelCA) {@literal <skye.viau@gmail.com>}
 */

public class User {

    /**
     * Load a user's profile, including game data and settings, returns null if errored.
     *
     * @param name The name of the user
     * @param password The password of the user (Plaintext)
     * @return User profile JSONObject
     */

    static JSONObject loadUser(String name, String password) {
        try {
            String fileContent = Files.readString(Paths.get(System.getProperty("user.home") + "/.spacestocc/" + name + ".stocc"));
            JSONTokener tokener = new JSONTokener(fileContent);
            JSONObject content = new JSONObject(tokener);

            if (content.getString("password").equals(password)) {
                return content;
            } else {
                return null;
            }
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * Create a new profile for a user
     *
     * @param name The name of the user
     * @param password The password of the user (Plaintext)
     * @return User profile JSONObject from loadUser, returns null if errored.
     * @see #loadUser
     */

    static JSONObject createUser(String name, String password) {
        try {
            new File(System.getProperty("user.home") + "/.spacestocc/").mkdirs();
            FileWriter write = new FileWriter((System.getProperty("user.home") + "/.spacestocc/" + name + ".stocc"));

            write.write("{\"name\": \"" + name + "\", \"password\": \"" + password + "\",  \"ownedStocks\":[], \"cashMoney\": \"10000\"}");
            write.close();

            return loadUser(name, password);
        } catch (Throwable e) {
            return null;
        }
    }
}
