package _1irda.socket.enums;

public enum Response {

    GOOD("GOOD"),
    BAD("BAD"),
    DONE("DONE"),
    ERROR("ERROR");

    private String value;

    Response(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
