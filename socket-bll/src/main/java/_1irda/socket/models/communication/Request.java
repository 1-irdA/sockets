package _1irda.socket.models.communication;

import static _1irda.socket.constants.Constants.*;
import static _1irda.socket.models.Analyzer.*;

/**
 * Request class
 */
public class Request {

    /**
     * Sent command
     */
    private final String command;

    /**
     * Sent login
     */
    private final String login;

    /**
     * Sent password
     */
    private final String password;

    /**
     * Token
     */
    private final String token;

    /**
     * @param data contains infos to extract
     */
    public Request(String data) {
        String[] items = extractInfos(data);
        command = items[CMD_INDEX];
        login = items[LOGIN_INDEX];
        password = items[PASSWORD_INDEX];
        token = TOKEN_DELIMITER + items[TOKEN_INDEX];
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
