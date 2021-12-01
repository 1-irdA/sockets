package _1irda.socket.models;

import _1irda.socket.enums.Status;

/**
 * Server response on authentication request
 */
public class AuthResponse {

    /**
     * Response status
     */
    private final String status;

    /**
     * Username to auth user (token)
     */
    private final String username;

    /**
     * @param status response status
     * @param username token
     */
    public AuthResponse(String status, String username) {
        this.status = status;
        this.username = status.equals(Status.GOOD.getValue()) ? username : "";
    }

    @Override
    public String toString() {
        return status + " " + username;
    }
}
