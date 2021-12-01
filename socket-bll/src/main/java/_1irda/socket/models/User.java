package _1irda.socket.models;

/**
 * User class used by client
 */
public class User {

    /**
     * User username
     */
    private String username;

    /**
     * @param username username
     */
    public User(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
