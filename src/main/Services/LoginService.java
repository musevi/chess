package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.UserDAO;
import Models.AuthToken;
import Requests.LoginRequest;
import Results.LoginResult;

/**
 * Service for Login, which logs in an existing user, and returns a new authToken.
 *
 */
public class LoginService {

    /**
     * Returns result based on request
     *
     * @param request   LoginRequest
     * @return          Returns LoginResult
     */
    public LoginResult login(LoginRequest request) {
        try {

            UserDAO userDAO = UserDAO.getInstance();
            userDAO.getUser(request.getUsername(), request.getPassword());

            AuthToken token = new AuthToken(request.getUsername());
            AuthTokenDAO authTokenDAO = AuthTokenDAO.getInstance();
            authTokenDAO.addAuthToken(token);

            return new LoginResult(token.getAuthToken(), request.getUsername());
        } catch (Exception e) {
            System.out.println(e);
            if(e.getMessage().equals("user not in database")) {
                return new LoginResult("Error: unauthorized");
            }
            return new LoginResult("Error: internal server error");
        }
    }
}
