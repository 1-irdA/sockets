package _1irda.socket.models;

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
        host = items[0];
        port = Integer.parseInt(items[1]);
        proto = items[2];
        type = items[3];
        login = items[4];
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
