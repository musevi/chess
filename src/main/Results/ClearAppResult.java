package Results;

/**
 * Result for ClearApp, which clears the database, removes all users, games, and authTokens
 */
public class ClearAppResult {
    private String message;

    /**
     * Constructor for ClearAppResult
     * @param message   result message
     */
    public ClearAppResult(String message) {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
