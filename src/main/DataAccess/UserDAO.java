package DataAccess;

import Models.AuthToken;
import Models.User;

import java.util.HashMap;

/**
 * User DAO
 */
public class UserDAO {

    private static HashMap<String, User> users;
    private static UserDAO single_instance = null;
    private UserDAO() {
        users = new HashMap<>();
    }
    public static synchronized UserDAO getInstance() {
        if(single_instance == null) {
            single_instance = new UserDAO();
        }
        return single_instance;
    }


    /**
     * Creates a user
     *
     * @param u     user
     * @throws DataAccessException
     */
    public void createUser(User u) throws DataAccessException {
        users.put(u.getUsername(), u);
    }

    /**
     * Reads username and password and returns a user
     *
     * @param username  user's username
     * @param password  user's password
     * @return
     * @throws DataAccessException
     */
    public User getUser(String username, String password) throws DataAccessException {
        User user = users.get(username);
        if(user == null) {throw new DataAccessException("user not in database");}
        if(user.getPassword().equals(password)) {
            return user;
        }
        throw new DataAccessException("user not in database");
        //return null;
    }

    /**
     * Checks if a user is already in the database
     *
     * @param username  username to check if exists
     * @throws DataAccessException
     */
    public void checkUserExists(String username) throws DataAccessException {
        if(users.containsKey(username)) {
            throw new DataAccessException("user already exists");
        }
    }

    /**
     * Deletes a user
     *
     * @param u     user
     * @throws DataAccessException
     */
    public void deleteUser(User u) throws DataAccessException {}

    public void clearAll() {
        users.clear();
    }

}
