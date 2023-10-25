package DataAccess;

/**
 * DAO for authToken
 */
public class AuthTokenDAO {

    /**
     * Creates an authToken
     *
     * @param authToken     authToken to create
     */
    void CreateAuthToken(Models.AuthToken authToken) {}

    /**
     * Reads and returns authToken from username
     * @param username  user's username
     * @return          user's authToken
     */
    Models.AuthToken ReadAuthToken(String username) {return null;}

    /**
     * Changes a user's authToken
     *
     * @param username  user's username
     */
    void ChangeAuthToken(String username) {}

    /**
     * Deletes a user's authToken
     * @param username  user's username
     */
    void DeleteAuthToken(String username) {}


}
