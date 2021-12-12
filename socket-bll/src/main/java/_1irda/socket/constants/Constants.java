package _1irda.socket.constants;

public class Constants {

    /**
     * Pattern to get cmd pattern
     */
    public static final String CMD_PATTERN = "^(ADD|CHK|DEL|MOD)";

    /**
     * Pattern to get resp pattern
     */
    public static final String RESP_PATTERN = "[^\s]+";

    /**
     * Pattern to get token
     */
    public static final String TOKEN_PATTERN = "[^:]*$";

    /**
     * Pattern to get login and password separately
     */
    public static final String LOGIN_PASS_PATTERN = "[^\s]+";

    /**
     * Token delimiter
     */
    public static final String TOKEN_DELIMITER = "token:";

    /**
     * Command index in items
     */
    public static final byte CMD_INDEX = 0;

    /**
     * Login index in items
     */
    public static final byte LOGIN_INDEX = 1;

    /**
     * Password index in items
     */
    public static final byte PASSWORD_INDEX = 2;

    /**
     * Token index in items
     */
    public static final byte TOKEN_INDEX = 3;
}
