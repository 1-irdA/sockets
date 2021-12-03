package _1irda.socket.enums;

public enum Status {

    GOOD("GOOD"),
    BAD("BAD"),
    DONE("DONE"),
    ERROR("ERROR");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
