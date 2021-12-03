package _1irda.sockets.models;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

/**
 * Class to log
 * Used by tcp and udp server
 */
public class JsonLogger {

    /**
     * Transform request to json
     * @param host client host
     * @param port client port
     * @param proto protocol
     * @param type request type
     * @param login login used
     * @param result operation result
     * @return a json object
     */
    private String reqToJson(String host, int port, String proto, String type, String login, String result) {
        return new JSONObject()
                .put("host", host)
                .put("port", port)
                .put("proto", proto)
                .put("type", type)
                .put("login", login)
                .put("result", result)
                .put("date", new Date().toString())
                .toString()
                + ",\n";
    }

    /**
     * Unique instance
     */
    private static JsonLogger logger = null;

    /**
     * Singleton
     * @return logger
     */
    private static JsonLogger getLogger() {
        if (logger == null) {
            logger = new JsonLogger();
        }
        return logger;
    }

    /**
     * Log method
     * @param host client host
     * @param port client port
     * @param proto protocol
     * @param type request type
     * @param login login
     * @param result operation result
     */
    public static void log(String host, int port, String proto, String type, String login, String result) throws IOException {
        JsonLogger logger = getLogger();
        Path path = Paths.get("../../logs.json");
        byte[] toWrite = logger.reqToJson(host, port, proto, type, login, result).getBytes();
        Files.write(path.getFileName(), toWrite, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}

