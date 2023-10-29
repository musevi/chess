package Results;

/**
 * Result for Logout, which logs out the user represented by the authToken
 */
public class LogoutResult {

    /**
     * message string
     */
    private String message;

    public LogoutResult() {}

    /**
     * Constructor for LogoutResult
     *
     * @param message   message for action
     */
    public LogoutResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
