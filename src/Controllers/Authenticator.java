package Controllers;

import java.util.HashMap;
import java.util.Map;

public class Authenticator {
    private static final Map<String, String> users = new HashMap<>();

    static {
        // Predefined users (username, password)
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    public static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public static void register(String username, String password) {
        users.put(username, password);
    }
}
