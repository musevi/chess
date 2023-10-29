package DataAccess;
import Models.AuthToken;
import java.util.HashMap;

/**
 * DAO for authToken
 */
public class AuthTokenDAO {
    private HashMap<String, AuthToken> authTokens;
    private static AuthTokenDAO single_instance = null;
    private AuthTokenDAO() {
        authTokens = new HashMap<>();
    }
    public static synchronized AuthTokenDAO getInstance() {
        if(single_instance == null) {
            single_instance = new AuthTokenDAO();
        }
        return single_instance;
    }



    /**
     * Creates an authToken
     *
     * @param authToken     authToken to create
     */
    public void addAuthToken(AuthToken authToken) {
        authTokens.put(authToken.getAuthToken(), authToken);
    }

    /**
     * Reads and returns authToken from username
     * @param authToken  user's authtoken string
     * @return          user's authToken
     */
    public AuthToken getAuthToken(String authToken) throws DataAccessException {
        if(!authTokens.containsKey(authToken)) {
            throw new DataAccessException("token not found");
        }
        return authTokens.get(authToken);
    }

    /**
     * Changes a user's authToken
     *
     * @param username  user's username
     */
    public void changeAuthToken(String username) throws DataAccessException {}

    /**
     * Deletes a user's authToken
     * @param authToken  user's authToken string
     */
    public void deleteAuthToken(String authToken) throws DataAccessException {
        authTokens.remove(authToken);
    }

    public void clearAll() {
        authTokens.clear();
    }
}
