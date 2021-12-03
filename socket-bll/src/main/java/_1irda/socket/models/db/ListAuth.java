package _1irda.socket.models.db;

import java.util.HashMap;

/**
 * Manage authentication list
 */
public class ListAuth {

    /**
     * Store login and password
     */
    private final HashMap<String, String> authEntries;

    /**
     * Init list
     */
    public ListAuth() {
        authEntries = new HashMap<>();
        /* add tests entries */
        authEntries.put("Toto", "Toto");
        authEntries.put("Titi", "Titi");
        authEntries.put("Tata", "Tata");
        authEntries.put("Tutu", "Tutu");
    }

    /**
     * Create login password
     * @param login : login
     * @param passwd : password
     * @return true if added
     */
    public synchronized boolean create(String login, String passwd) {
        if(authEntries.containsKey(login)) {
            return false;
        }
        return authEntries.put(login, passwd) == null;
    }

    /**
     * Update login password
     * @param login : login
     * @param passwd : password
     * @return true if updated
     */
    public synchronized boolean update(String login, String passwd) {
        if(!authEntries.containsKey(login)) {
            return false;
        }
        String oldPass = authEntries.get(login);
        return authEntries.put(login, passwd).equals(oldPass);
    }

    /**
     * Delete login password
     * @param login : login
     * @param passwd : password
     * @return true if deleted
     */
    public synchronized boolean delete(String login, String passwd) {
        if(!test(login, passwd)) {
            return false;
        }
        return authEntries.remove(login).equals(passwd);
    }

    /**
     * Test login and password
     * @param login : login
     * @param passwd : password
     * @return true if is valid login and password
     */
    public synchronized boolean test(String login, String passwd) {
        if(!authEntries.containsKey(login)) {
            return false;
        }
        return authEntries.get(login).equals(passwd);
    }
}
