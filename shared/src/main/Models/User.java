package Models;

/**
 * A user to play or spectate a chess game
 */
public class User {

    /**
     * username for user
     */
    private String username;

    /**
     * user's password
     */
    private String password;

    /**
     * user's email
     */
    private String email;

    /**
     * Constructor for User
     *
     * @param username  user's username
     * @param password  user's password
     * @param email     user's email
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
