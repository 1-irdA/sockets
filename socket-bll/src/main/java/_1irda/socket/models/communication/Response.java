package _1irda.socket.models.communication;

import _1irda.socket.enums.Status;

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
    private String username;

    /**
     * @param data status + [error message] + [token]
     */
    public Response(String data) {
        String[] items = data.split(" ");
        status = data;
        username = "";

        if (data.contains(Status.GOOD.getValue())) {
            if (items.length == 2) {
                status = items[0];
                username = items[1];
            }
        } else if (data.contains(Status.BAD.getValue())) {
            status = items[0];
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
        return status + " " + username;
    }
}
