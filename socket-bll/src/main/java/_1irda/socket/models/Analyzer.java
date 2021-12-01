package _1irda.socket.models;

import _1irda.socket.enums.Status;

import java.util.Arrays;

public class Analyzer {

    /**
     * Array index to find username
     */
    private static final int USERNAME = 1;

    /**
     * Contains login, password and methods to manage them
     */
    private final ListAuth listAuth;

    /**
     * @param listAuth contains login, password and method to manage them
     */
    public Analyzer(ListAuth listAuth) {
        this.listAuth = listAuth;
    }

    /**
     * Get the user command
     * @param items split string (cmd username password token:{username})
     * @return the user command
     */
    private String getCommand(String[] items) {
        return items.length > 3 ? items[0] : "";
    }

    /**
     * Launch operation following command in string
     * @param data to analyse
     * @return the result of the operation or error
     */
    public String checkCommand(String data) {
        String[] items = data.split(" ");
        String command = getCommand(items);
        boolean isManager = isAuth(items);
        String result = error();

        if (command.equals("CHK")) {
            result = new AuthResponse(checkLoginPassword(items), items[USERNAME]).toString();
        } else if (command.equals("ADD") && isManager) {
            result = addLoginPassword(items);
        } else if (command.equals("DEL") && isManager) {
            result = deleteLoginPassword(items);
        } else if (command.equals("MOD") && isManager) {
            result = updatePassword(items);
        }
        return result.trim();
    }

    /**
     * Check if user is authenticated
     * @param items split string
     * @return true if logged, false else
     */
    private boolean isAuth(String[] items) {
        String token = Arrays.stream(items)
                .filter(i -> i.contains("token:"))
                .findFirst()
                .get();
        String[] parts = token.split("token:");
        token = parts.length == 2 ? parts[1]: "";
        return !token.equals("");
    }

    /**
     * @return error message
     */
    private String error() {
        return Status.ERROR.getValue() + " Unknown command or auth required to ADD, DEL, MOD";
    }

    /**
     * Check if the couple of login / password are in list
     * @param items [command, login, password, token]
     * @return 'GOOD' if in, 'BAD' else
     */
    private String checkLoginPassword(String[] items) {
        return listAuth.test(items[1], items[2])
                ? Status.GOOD.getValue()
                : Status.BAD.getValue();
    }

    /**
     * Add a login / password in list
     * @param items [command, login, password, token]
     * @return 'DONE' if added, 'ERROR' else
     */
    private String addLoginPassword(String[] items) {
        return listAuth.create(items[1], items[2])
                ? Status.DONE.getValue()
                : Status.ERROR.getValue() + " cannot be added";
    }

    /**
     * Delete login password
     * @param items [command, login, password, token]
     * @return 'DONE' if deleted, 'ERROR' else
     */
    private String deleteLoginPassword(String[] items) {
        return  listAuth.delete(items[1], items[2])
                ? Status.DONE.getValue()
                : Status.ERROR.getValue() + " cannot be deleted";
    }

    /**
     * Update password with login and new password
     * @param items [command, login, password, token]
     * @return 'DONE' if updated, 'ERROR' else
     */
    private String updatePassword(String[] items) {
        return listAuth.update(items[1], items[2])
                ? Status.DONE.getValue()
                : Status.ERROR.getValue() + " cannot be updated";
    }
}
