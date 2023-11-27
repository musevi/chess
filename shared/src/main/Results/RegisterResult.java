package Results;

/**
 * Result for Register, which registers a new user
 */
public class RegisterResult {

    /**
     * message string
     */
    private String message;

    /**
     * authToken string
     */
    private String authToken;

    /**
     * username string
     */
    private String username;

    public RegisterResult(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    /**
     * Constructor for RegisterResult
     *
     * @param message       message for action
     */
    public RegisterResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
