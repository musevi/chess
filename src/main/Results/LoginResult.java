package Results;

/**
 * Result for Login, which logs in an existing user, and returns a new authToken.
 *
 */
public class LoginResult {

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

    /**
     * Constructor for LoginResult
     *
     * @param message       message for action
     * @param authToken     user's authtoken
     * @param username      user's username
     */
    public LoginResult(String message, String authToken, String username) {}

    public String getMessage() {
        return message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
