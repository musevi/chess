package Models;
import java.util.*;

/**
 * An authtoken to be assigned to a player
 */
public class AuthToken {
    private String authToken;
    private String username;

    public AuthToken(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    /**
     * Constructor for authToken
     *
     * @param username  user's username
     */
    public AuthToken(String username) {
        GenerateToken();
        this.username = username;
    }

    /**
     * generates a token given a username
     *
     */
    private void GenerateToken() {
        authToken = UUID.randomUUID().toString();
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
