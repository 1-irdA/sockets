package _1irda.socket.models;

import _1irda.socket.enums.Response;
import _1irda.socket.models.ListAuth;

public class Analyzer {

    private final ListAuth listAuth;

    public Analyzer(ListAuth listAuth) {
        this.listAuth = listAuth;
    }

    private String getCommand(String[] items) {
        return items.length == 3 ? items[0] : "";
    }

    public String checkCommand(String data, boolean isManager) {
        String[] items = data.split(" ");
        String command = getCommand(items);
        String result = error();

        if (command.equals("CHK")) {
            result = checkLoginPassword(items);
        } else if (command.equals("ADD") && isManager) {
            result = addLoginPassword(items);
        } else if (command.equals("DEL") && isManager) {
            result = deleteLoginPassword(items);
        } else if (command.equals("MOD") && isManager) {
            result = updatePassword(items);
        }
        return result;
    }

    private String error() {
        return Response.ERROR.getValue() + " Unknown command or auth required to ADD, DEL, MOD";
    }

    private String checkLoginPassword(String[] items) {
        return isGoodLoginPassword(items) ? Response.GOOD.getValue() : Response.BAD.getValue();
    }

    private boolean isGoodLoginPassword(String[] items) {
        return listAuth.test(items[1], items[2]);
    }

    private String addLoginPassword(String[] items) {
        return add(items) ? Response.DONE.getValue() : Response.ERROR.getValue() + " cannot be added";
    }

    private String deleteLoginPassword(String[] items) {
        return delete(items) ? Response.DONE.getValue() : Response.ERROR.getValue() + " cannot be deleted";
    }

    private String updatePassword(String[] items) {
        return update(items) ? Response.DONE.getValue() : Response.ERROR.getValue() + " cannot be updated";
    }

    private boolean add(String[] items) {
        return listAuth.create(items[1], items[2]);
    }

    private boolean delete(String[] items) {
        return listAuth.delete(items[1], items[2]);
    }

    private boolean update(String[] items) {
        return listAuth.update(items[1], items[2]);
    }
}
