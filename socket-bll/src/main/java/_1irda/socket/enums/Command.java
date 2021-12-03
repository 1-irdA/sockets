package _1irda.socket.enums;

public enum Command {

    CHK("CHK"),
    ADD("ADD"),
    MOD("MOD"),
    DEL("DEL");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
