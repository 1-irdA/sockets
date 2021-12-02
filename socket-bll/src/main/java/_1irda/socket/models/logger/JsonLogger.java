package _1irda.socket.models.logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

/**
 * Class to log
 * Used by tcp and udp server
 */
public class JsonLogger {

    private static final Path path = Paths.get("../../logs.json");

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
    private JsonObject reqToJson(String host, int port, String proto, String type, String login, String result) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("host", host)
                .add("port", port)
                .add("proto", proto)
                .add("type", type)
                .add("login", login)
                .add("result", result)
                .add("date", new Date().toString());
        return builder.build();
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
        byte[] toWrite = logger.reqToJson(host, port, proto, type, login, result).toString().getBytes();
        Files.write(path.getFileName(), toWrite, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}

