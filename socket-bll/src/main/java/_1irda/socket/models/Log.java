package _1irda.socket.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static _1irda.socket.constants.Constants.TOKEN_PATTERN;

public class Log {

    /**
     * Host
     */
    private final String host;

    /**
     * Port
     */
    private final int port;

    /**
     * Protocol
     */
    private final String proto;

    /**
     * Command
     */
    private final String type;

    /**
     * Username
     */
    private final String login;

    /**
     * Request result
     */
    private final String result;

    public Log(String[] items) {
        Matcher tokenMatcher = Pattern.compile(TOKEN_PATTERN).matcher(items[4]);
        host = items[0];
        port = Integer.parseInt(items[1]);
        proto = items[2];
        type = items[3];
        login = tokenMatcher.find() ? tokenMatcher.group() : "";
        result = items[5];
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getProto() {
        return proto;
    }

    public String getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public String getResult() {
        return result;
    }
}
