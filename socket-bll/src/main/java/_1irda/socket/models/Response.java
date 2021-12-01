package _1irda.socket.models;

import _1irda.socket.enums.Status;

/**
 * Response class used by client
 */
public class Response {

    /**
     * Response status
     */
    private final String status;

    /**
     * Token
     */
    private final String username;

    /**
     * @param data status + [error message] + [token]
     */
    public Response(String data) {
        if (data.contains(Status.GOOD.getValue())) {
            String[] items = data.split(" ");
            status = items[0];
            username = items[1];
        } else {
            status = data;
            username = "";
        }
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return status;
    }
}
