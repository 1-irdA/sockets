package _1irda.socket.models.communication;

import _1irda.socket.enums.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static _1irda.socket.constants.Constants.RESP_PATTERN;
import static _1irda.socket.constants.Constants.TOKEN;

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
    private final String token;

    /**
     * @param data status + [error message] + [token]
     */
    public Response(String data) {
        Matcher matcherResp = Pattern.compile(RESP_PATTERN).matcher(data);
        status = matcherResp.find() ? matcherResp.group() : "";
        token = matcherResp.find() ? matcherResp.group() : "";

        if (status.equals(Status.ERROR.getValue())) {
            status = data;
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
        return status + " " + TOKEN + token;
    }
}
