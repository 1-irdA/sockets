package _1irda.socket.models.communication;

import _1irda.socket.models.Analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static _1irda.socket.constants.Constants.*;

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

    public Request(String data) {
        String[] items = Analyzer.extractInfos(data);
        command = items[CMD_INDEX];
        login = items[LOGIN_INDEX];
        password = items[PASSWORD_INDEX];
        token = items[TOKEN_INDEX];
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
