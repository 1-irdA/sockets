package _1irda.socket.enums;

/**
 * Represent user command
 */
public enum Command {

    /**
     * Check login password
     */
    CHK("CHK"),

    /**
     * Add login password
     */
    ADD("ADD"),

    /**
     * Update login password
     */
    MOD("MOD"),

    /**
     * Delete login password
     */
    DEL("DEL");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
