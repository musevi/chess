package Requests;

/**
 * Request for Login, which logs in an existing user, and returns a new authToken.
 *
 */
public class LoginRequest {

    /**
     * username string
     */
    private String username;

    /**
     * password string
     */
    private String password;

    /**
     * authToken for request;
     */
    private Models.AuthToken authToken;

    /**
     * Constructor for LoginRequest
     *
     * @param username  set the username
     * @param password  set the password
     */
    public LoginRequest(String username, String password) {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
