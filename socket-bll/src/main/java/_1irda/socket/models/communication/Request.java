package _1irda.socket.models.communication;

/**
 * Request class
 */
public class Request {

    /**
     * Sent command
     */
    private String command;

    /**
     * Sent login
     */
    private String login;

    /**
     * Sent password
     */
    private String password;

    /**
     * Token
     */
    private String token;

    public Request(String data) {
        String[] items = data.split(" ");

        /* command login password token:{""|username} */
        if (items.length == 4) {
            this.command = items[0];
            this.login = items[1];
            this.password = items[2];
            this.token = items[3];
        } else if (items.length == 3) {
            /* command login password */
            this.command = items[0];
            this.login = items[1];
            this.password = items[2];
            this.token = "";
        }
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return command + " " + login + " " + password + " " + token;
    }
}
