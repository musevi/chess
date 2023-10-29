package Results;

/**
 * Result for ClearApp, which clears the database, removes all users, games, and authTokens
 */
public class ClearAppResult {
    private String message;

    public ClearAppResult() {}
    public ClearAppResult(String message) {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
