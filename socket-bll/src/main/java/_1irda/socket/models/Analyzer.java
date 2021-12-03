package _1irda.socket.models;

import _1irda.socket.enums.Status;
import _1irda.socket.models.communication.Response;
import _1irda.socket.models.db.ListAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static _1irda.socket.constants.Constants.*;

public class Analyzer {

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
     * Launch operation following command in string
     * @param data to analyse
     * @return the result of the operation or error
     */
    public String checkCommand(String data) {
        String[] items = extractInfos(convertData(data));
        boolean isManager = !items[TOKEN_INDEX].isEmpty();
        String result = error();

        if (items[CMD_INDEX].equals("CHK")) {
            result = new Response(checkLoginPassword(items) + " " + items[LOGIN_INDEX]).toString();
        } else if (items[CMD_INDEX].equals("ADD") && isManager) {
            result = addLoginPassword(items);
        } else if (items[CMD_INDEX].equals("DEL") && isManager) {
            result = deleteLoginPassword(items);
        } else if (items[CMD_INDEX].equals("MOD") && isManager) {
            result = updatePassword(items);
        }
        return result.trim();
    }

    public static String[] extractInfos(String data) {
        Matcher matcherCmd = Pattern.compile(CMD_PATTERN).matcher(data);
        Matcher matcherToken = Pattern.compile(TOKEN_PATTERN).matcher(data);
        String command = matcherCmd.find() ? matcherCmd.group(0) : "";
        String token = matcherToken.find() ? matcherToken.group(0) : TOKEN;
        String loginPass = data.substring(command.length(), data.indexOf(TOKEN));
        Matcher matcherLoginPass = Pattern.compile(LOGIN_PASS_PATTERN).matcher(loginPass);
        String login = matcherLoginPass.find() ? matcherLoginPass.group(0) : "";
        String password = matcherLoginPass.find() ? matcherLoginPass.group(0) : "";
        return new String[] {command, login, password, token};
    }

    private String convertData(String data) {
        return data.contains(TOKEN) ? data : data + TOKEN;
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
        return listAuth.test(items[LOGIN_INDEX], items[PASSWORD_INDEX])
                ? Status.GOOD.getValue()
                : Status.BAD.getValue();
    }

    /**
     * Add a login / password in list
     * @param items [command, login, password, token]
     * @return 'DONE' if added, 'ERROR' else
     */
    private String addLoginPassword(String[] items) {
        return listAuth.create(items[LOGIN_INDEX], items[PASSWORD_INDEX])
                ? Status.DONE.getValue()
                : Status.ERROR.getValue() + " cannot be added";
    }

    /**
     * Delete login password
     * @param items [command, login, password, token]
     * @return 'DONE' if deleted, 'ERROR' else
     */
    private String deleteLoginPassword(String[] items) {
        return  listAuth.delete(items[LOGIN_INDEX], items[PASSWORD_INDEX])
                ? Status.DONE.getValue()
                : Status.ERROR.getValue() + " cannot be deleted";
    }

    /**
     * Update password with login and new password
     * @param items [command, login, password, token]
     * @return 'DONE' if updated, 'ERROR' else
     */
    private String updatePassword(String[] items) {
        return listAuth.update(items[LOGIN_INDEX], items[PASSWORD_INDEX])
                ? Status.DONE.getValue()
                : Status.ERROR.getValue() + " cannot be updated";
    }
}
