package _1irda.socket.models.communication;

import _1irda.socket.enums.Command;

import java.util.Arrays;

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
            this.command = Arrays.stream(Command.values())
                    .map(c -> c.getValue())
                    .toList()
                    .contains(items[0]) ? items[0] : "";
            this.login = items[1];
            this.password = items[2];
            this.token = items[3];
        }
    }

    public String getCommand() {
        return command;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return command + " " + login + " " + password + " " + token;
    }
}
