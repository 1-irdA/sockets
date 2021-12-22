package _1irda.socket.models.communication;

import _1irda.socket.enums.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static _1irda.socket.constants.Constants.*;

/**
 * Response class
 */
public class Response {

    /**
     * Response status
     */
    private String status;

    /**
     * Token
     */
    private String token;

    /**
     * @param data status + [error message] + [token]
     */
    public Response(String data) {
        Matcher matcherResp = Pattern.compile(SPACES_PATTERN).matcher(data);
        status = matcherResp.find() ? matcherResp.group().trim() : "";
        token = matcherResp.find() ? matcherResp.group().trim() : "";

        if (status.equals(Status.ERROR.getValue())) {
            status = data;
        } else if (status.equals(Status.BAD.getValue())) {
            token = "";
        }
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return status + " " + token;
    }
}
