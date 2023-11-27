package Requests;

/**
 * Request for Register, which registers a new user
 */
public class RegisterRequest {

    /**
     * username string
     */
    private String username;

    /**
     * password string
     */
    private String password;

    /**
     * email string
     */
    private String email;

    /**
     * Constructor for RegisterRequest
     *
     * @param username      user's username
     * @param password      user's password
     * @param email         user's email
     */
    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
